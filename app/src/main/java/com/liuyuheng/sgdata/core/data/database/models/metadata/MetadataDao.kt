package com.liuyuheng.sgdata.core.data.database.models.metadata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MetadataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(metadataEntity: MetadataEntity)

    @Query("SELECT value FROM metadata WHERE `key` = :key LIMIT 1")
    suspend fun getValue(key: String): String?
}