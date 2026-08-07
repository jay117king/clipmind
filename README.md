# ClipMind — Screenshot & Link Memory (MVP)

> See something → save it → find it later by asking.

ClipMind is a personal "second brain" for screenshots and links. Capture anything on your phone, automatically process it (OCR, summaries, embeddings), and query your entire history with natural language.

## The Core Loop

1. **Screenshot** anything on your phone → auto-saved, OCR'd, and semantically indexed  
2. **Copy a link** anywhere → auto-captured, summarized, and stored  
3. **Ask** natural-language questions across everything you've ever clipped

## MVP Feature Set

| Trigger | What Happens | Storage |
|---|---|---|
| **Screenshot** | App detects new screenshot via iOS Shortcuts / Android Accessibility → OCR extracts text + image embedding → tags topics automatically | Saved to personal vector DB with source timestamp |
| **Link copied** | Clipboard monitor catches URL → fetches page → generates summary + extracts key quotes + stores full text | Same unified index |
| **Manual ask** | Chat UI queries your clip index with RAG — "What was that restaurant Sarah recommended?" or "Summarize all articles I saved about AI agents last week" | Returns synthesized answer with source clips |

## What Makes It "Second Brain"

Unlike Apple Photos or Pocket, clips aren't siloed. A screenshot of a flight confirmation, a copied recipe link, and a screenshot of a Slack message all live in one queryable memory layer. The AI knows *you* saved them, *when*, and *in what context*.

## Technical Stack (MVP)

- **Mobile app**: React Native or Flutter
- **Screenshot capture**: iOS Shortcuts automation + Android Accessibility API (no custom keyboard needed)
- **Link ingestion**: Background clipboard listener + share-sheet fallback
- **Processing**: OCR (Tesseract / Apple Vision), web scraping (Jina AI or Firecrawl), embedding model (small local or API)
- **Memory**: SQLite + simple vector search (e.g. `sqlite-vec`) or Pinecone free tier
- **Chat**: Lightweight RAG with GPT-4o-mini / Claude Haiku

## MVP Boundaries (What We Cut)

- ❌ No hardware device
- ❌ No calendar/email integrations (Phase 2)
- ❌ No real-time collaboration
- ❌ No advanced graph relationships — just semantic search + basic tagging
- ✅ **One job**: See something → save it → find it later by asking

## Open Questions

1. **Platform priority** — iOS-first, Android-first, or both from day one? (Accessibility APIs differ significantly.)
2. **Privacy model** — Process everything on-device, or cloud-based with encryption? (This changes the embedding/OCR stack entirely.)
3. **Monetization angle** — Freemium with local storage free + cloud sync paid, or pure subscription from launch?

## Project Status

This repository is the starting point for the ClipMind MVP. Structure and initial code will be added as development begins.

---

*Built with the goal of making personal knowledge instantly queryable.*
