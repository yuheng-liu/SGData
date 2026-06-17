package com.liuyuheng.sgdata.core.data.database.models.metadata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "metadata")
data class MetadataEntity(
    @PrimaryKey val key: String,
    val value: String
)
