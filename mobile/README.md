# ClipMind Android App

Native Android implementation of ClipMind using **Kotlin + Jetpack Compose**.

## Requirements

- Android Studio Ladybug (2024.2+) or newer
- JDK 17+
- Android SDK with API 26+

## Getting Started

1. Clone the repo and open the **`mobile/`** folder in Android Studio  
   (`File → Open → select the mobile directory`)
2. Let Gradle sync (it will download dependencies via the version catalog)
3. Create a virtual device (API 26+) or connect a physical phone
4. Click **Run**

> **Note**: You will still need to generate the default launcher icons (`mipmap`) if Android Studio complains.  
> You can do this via `File → New → Image Asset` or temporarily comment out the icon lines in the Manifest.

## Current Project Structure

```
mobile/
├── app/
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/clipmind/app/
│       │   ├── ClipMindApp.kt
│       │   ├── MainActivity.kt
│       │   ├── data/local/
│       │   │   ├── ClipEntity.kt
│       │   │   ├── ClipDao.kt
│       │   │   └── ClipMindDatabase.kt
│       │   ├── domain/model/Clip.kt
│       │   ├── service/
│       │   │   ├── ScreenshotObserver.kt
│       │   │   └── ClipMindAccessibilityService.kt
│       │   └── ui/
│       │       ├── chat/ChatScreen.kt
│       │       ├── clips/ClipsScreen.kt
│       │       └── theme/
│       │           ├── Theme.kt
│       │           └── Type.kt
│       └── res/
│           ├── values/
│           └── xml/accessibility_service_config.xml
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradle/libs.versions.toml
```

## What’s Already Scaffolded

- Full Gradle setup with Version Catalog
- AndroidManifest with permissions + Share Intent + Accessibility Service
- Room database + Entity + DAO
- Domain model (`Clip`)
- ScreenshotObserver + AccessibilityService skeletons
- Compose Theme
- Basic **Chat** screen (with message list + input)
- Basic **Clips** list screen (with sample data)
- Navigation between Chat ↔ Clips

## Next Development Steps

1. Wire Room database in `ClipMindApp`
2. Implement real screenshot detection + ML Kit OCR
3. Implement clipboard + share-intent link ingestion
4. Connect ChatScreen to a ViewModel + simple RAG / search
5. Add Settings screen and permission request flows

## Key Dependencies

- Jetpack Compose + Material 3
- Navigation Compose
- Room + KSP
- WorkManager
- ML Kit Text Recognition
- Coil, OkHttp, Coroutines
