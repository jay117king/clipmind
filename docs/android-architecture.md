# ClipMind Android Architecture (MVP)

## Goals

- Fully offline-capable core experience
- Minimal battery & privacy impact
- Fast iteration with Jetpack Compose
- Clear path to add cloud sync later

## High-Level Architecture

```
┌─────────────────────────────────────────────────────┐
│                    UI Layer (Compose)               │
│  ChatScreen │ ClipsListScreen │ SettingsScreen      │
└──────────────────────────┬──────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────┐
│                 ViewModels (MVVM)                   │
│  ChatViewModel │ ClipsViewModel │ CaptureViewModel  │
└──────────────────────────┬──────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────┐
│                   Domain / Use Cases                │
│  ProcessScreenshot │ IngestLink │ QueryClips (RAG)  │
└──────────────────────────┬──────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────┐
│                    Data Layer                       │
│  Room Database (Clips + Embeddings)                 │
│  ML Kit OCR  │  Embedding Provider  │  Web Scraper  │
└─────────────────────────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────┐
│               Background Services                   │
│  ScreenshotObserver (MediaStore)                    │
│  ClipboardMonitor                                   │
│  AccessibilityService (fallback)                    │
│  WorkManager jobs                                   │
└─────────────────────────────────────────────────────┘
```

## Key Components

### 1. Screenshot Capture
- Primary: `ContentObserver` on `MediaStore.Images.Media.EXTERNAL_CONTENT_URI`
- Filter images whose path contains "Screenshot" or are in the Screenshots folder
- Fallback: Accessibility Service that detects the system screenshot action
- On new screenshot → enqueue WorkManager job for OCR + embedding

### 2. Link Capture
- `ClipboardManager.OnPrimaryClipChangedListener` (with Android version caveats)
- Share Intent filter (`text/plain`, `text/uri-list`)
- When URL detected → fetch page content (Jina AI / Firecrawl / simple Jsoup) → summarize → store

### 3. Storage (Room)

```kotlin
@Entity
data class Clip(
    @PrimaryKey val id: String,
    val type: ClipType,          // SCREENSHOT, LINK
    val rawText: String,
    val summary: String?,
    val sourceUri: String?,      // image path or URL
    val embedding: FloatArray?,  // or stored separately
    val tags: List<String>,
    val createdAt: Long,
    val updatedAt: Long
)
```

### 4. Vector Search (MVP approach)
- Store embeddings as BLOB or separate table
- For small datasets: load into memory and do cosine similarity
- Later: integrate `sqlite-vec` or a proper vector extension

### 5. Chat / RAG Flow
1. User asks a question
2. Embed the question
3. Retrieve top-k similar clips
4. Build a prompt with the retrieved context + question
5. Call LLM (GPT-4o-mini / Claude Haiku)
6. Return answer + source clips

## Permissions Required

- `READ_MEDIA_IMAGES` (Android 13+)
- `POST_NOTIFICATIONS` (for background status)
- Accessibility Service (optional but recommended for robust screenshot detection)
- Internet (for link fetching + LLM)

## Privacy Stance (Proposed)

- All OCR and local search happen on-device
- Embeddings can be local or sent to embedding API (user choice)
- LLM calls go to the chosen provider (user must supply key or use freemium tier)
- No data leaves the device unless the user enables cloud sync (Phase 2)

## Next Implementation Milestones

1. Project scaffold + Room + basic Compose screens
2. Screenshot observer + ML Kit OCR → save Clip
3. Clipboard + Share Intent → save Link Clip
4. Simple semantic search over stored clips
5. Chat UI + basic RAG
6. Polish, settings, and Play Store preparation
