package com.clipmind.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClipDao {

    @Query("SELECT * FROM clips ORDER BY createdAt DESC")
    fun getAllClips(): Flow<List<ClipEntity>>

    @Query("SELECT * FROM clips WHERE id = :id")
    suspend fun getClipById(id: String): ClipEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clip: ClipEntity)

    @Query("DELETE FROM clips WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM clips WHERE rawText LIKE '%' || :query || '%' OR summary LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    suspend fun search(query: String): List<ClipEntity>
}
