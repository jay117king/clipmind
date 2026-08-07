package com.clipmind.app.domain.model

/**
 * Core domain model representing a saved screenshot or link.
 */
data class Clip(
    val id: String,
    val type: ClipType,
    val rawText: String,
    val summary: String? = null,
    val sourceUri: String? = null,      // local image path or original URL
    val embedding: FloatArray? = null,
    val tags: List<String> = emptyList(),
    val createdAt: Long,
    val updatedAt: Long = createdAt
)

enum class ClipType {
    SCREENSHOT,
    LINK
}
