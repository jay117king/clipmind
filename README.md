# ClipMind — Screenshot & Link Memory (Android MVP)

> See something → save it → find it later by asking.

ClipMind is a personal "second brain" for screenshots and links on Android. Capture anything on your phone, automatically process it (OCR, summaries, embeddings), and query your entire history with natural language.

## Platform Decision

**Android-first.**  
We are building a native Android app using Kotlin + Jetpack Compose.

## Get the app on your phone (no computer needed)

Every time we push code, GitHub automatically builds a debug APK.

1. Open **https://github.com/jay117king/clipmind/actions** on your phone
2. Wait for the latest **“Build Debug APK”** run to finish (green check)
3. Download the **clipmind-debug-apk** artifact
4. Install the APK on your phone

Full instructions: [docs/how-to-get-the-app-on-your-phone.md](docs/how-to-get-the-app-on-your-phone.md)

## The Core Loop

1. **Screenshot** anything on your phone → auto-saved, OCR'd, and semantically indexed  
2. **Copy a link** anywhere → auto-captured, summarized, and stored  
3. **Ask** natural-language questions across everything you've ever clipped

## MVP Feature Set

| Trigger | What Happens | Storage |
|---|---|---|
| **Screenshot** | App detects new screenshot via MediaStore observer + Accessibility Service → OCR extracts text + image embedding → tags topics automatically | Saved to personal local vector DB with source timestamp |
| **Link copied** | Clipboard monitor catches URL → fetches page → generates summary + extracts key quotes + stores full text | Same unified index |
| **Manual ask** | Chat UI queries your clip index with RAG — "What was that restaurant Sarah recommended?" or "Summarize all articles I saved about AI agents last week" | Returns synthesized answer with source clips |

## What Makes It "Second Brain"

Unlike Google Photos or Pocket, clips aren't siloed. A screenshot of a flight confirmation, a copied recipe link, and a screenshot of a Slack message all live in one queryable memory layer. The AI knows *you* saved them, *when*, and *in what context*.

## Technical Stack (Android MVP)

- **Language & UI**: Kotlin + Jetpack Compose
- **Architecture**: MVVM
- **Screenshot capture**: MediaStore ContentObserver + Accessibility Service (fallback)
- **Link ingestion**: ClipboardManager listener + Share Intent / Share Sheet
- **OCR**: Google ML Kit Text Recognition (on-device)
- **Embeddings**: Local (ONNX / MediaPipe) or API (text-embedding-3-small)
- **Memory**: Room (SQLite) + vector search (sqlite-vec or in-memory cosine similarity)
- **Background work**: WorkManager
- **Chat / RAG**: Lightweight RAG with GPT-4o-mini or Claude Haiku (API)
- **Min SDK**: 26 (Android 8.0)

## MVP Boundaries (What We Cut)

- ❌ No iOS version yet
- ❌ No hardware device
- ❌ No calendar/email integrations (Phase 2)
- ❌ No real-time collaboration
- ❌ No advanced graph relationships — just semantic search + basic tagging
- ✅ **One job**: See something → save it → find it later by asking

## Open Decisions (still to finalize)

1. **Privacy model** — Fully on-device vs hybrid (local + optional encrypted cloud sync)
2. **Monetization** — Freemium (local free + cloud / better models paid) or pure subscription

## Project Structure

```
clipmind/
├── mobile/                 # Android app (Kotlin + Compose)
├── docs/                   # Architecture & decisions
├── .github/workflows/      # Automatic APK builds
└── README.md
```

---

*Built with the goal of making personal knowledge instantly queryable on Android.*
