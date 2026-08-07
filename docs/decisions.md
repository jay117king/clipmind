# Product Decisions

## Platform
**Android-first** (confirmed)

We are building a native Android app with Kotlin + Jetpack Compose.  
iOS version is deferred to a later phase.

## Privacy Model
**Local-first with optional cloud later**

- OCR, storage, and basic search run fully on-device
- Embeddings and LLM calls may use external APIs (user-controlled)
- Cloud sync / multi-device is Phase 2

## Monetization
**Freemium (to be confirmed)**

Proposed model:
- Free: Local storage + basic OCR + limited LLM queries
- Paid: Higher query limits, better models, cloud sync, priority processing

## Minimum SDK
**API 26 (Android 8.0)**

Good coverage while allowing modern APIs (MediaStore, WorkManager, etc.).

## Primary Capture Methods
1. MediaStore ContentObserver for screenshots
2. Accessibility Service as robust fallback
3. Clipboard listener + Share Intent for links
