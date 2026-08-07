# ClipMind Android App

Native Android implementation of ClipMind using **Kotlin + Jetpack Compose**.

## Requirements

- Android Studio Ladybug or newer (or latest stable)
- JDK 17+
- Android SDK with API 26+

## Getting Started

1. Open Android Studio
2. Select **Open** and choose this `mobile/` folder
3. Let Gradle sync
4. Run on an emulator or physical device (API 26+)

> **Note**: This folder currently contains the planned structure and starter files.  
> Full Android project files (build.gradle.kts, AndroidManifest, etc.) will be added next.

## Planned Package Structure

```
com.clipmind.app
├── data/
│   ├── local/          # Room database, DAOs
│   ├── repository/
│   └── remote/         # Optional API clients
├── domain/
│   ├── model/
│   └── usecase/
├── ui/
│   ├── chat/
│   ├── clips/
│   ├── settings/
│   └── theme/
├── service/
│   ├── ScreenshotObserver
│   ├── ClipboardMonitor
│   └── ClipMindAccessibilityService
└── util/
```

## Key Dependencies (planned)

- Jetpack Compose BOM
- Room
- ML Kit Text Recognition
- WorkManager
- Lifecycle / ViewModel
- Navigation Compose
- Coil (image loading)
- OkHttp / Retrofit (for link fetching & LLM)

## Development Order

1. Project scaffold + Room + basic screens
2. Screenshot detection + OCR pipeline
3. Link capture (clipboard + share)
4. Local search
5. Chat + RAG
