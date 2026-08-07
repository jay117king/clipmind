package com.clipmind.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.clipmind.app.domain.model.ClipType

@Entity(tableName = "clips")
data class ClipEntity(
    @PrimaryKey val id: String,
    val type: String,               // stored as string for simplicity
    val rawText: String,
    val summary: String? = null,
    val sourceUri: String? = null,
    val embedding: ByteArray? = null, // serialized FloatArray
    val tags: String = "",          // comma-separated for MVP
    val createdAt: Long,
    val updatedAt: Long
)
