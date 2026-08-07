package com.clipmind.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ClipEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ClipMindDatabase : RoomDatabase() {
    abstract fun clipDao(): ClipDao
}
