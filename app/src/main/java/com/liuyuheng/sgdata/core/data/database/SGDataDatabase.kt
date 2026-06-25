package com.liuyuheng.sgdata.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkInfoDao
import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkInfoEntity
import com.liuyuheng.sgdata.core.data.models.dao.MetadataDao
import com.liuyuheng.sgdata.core.data.models.dao.MetadataEntity

/**
 * Main app Database object
 */

@Database(
    entities = [CarparkInfoEntity::class, MetadataEntity::class],
    version = 1
)
@TypeConverters(RoomTypeConverters::class)
abstract class SGDataDatabase : RoomDatabase() {

    abstract fun carparkInfoDao(): CarparkInfoDao

    abstract fun metadataDao(): MetadataDao
}