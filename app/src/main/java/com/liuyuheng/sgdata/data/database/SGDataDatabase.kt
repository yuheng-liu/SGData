package com.liuyuheng.sgdata.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.liuyuheng.sgdata.data.database.carparkinfo.CarparkInfoDao
import com.liuyuheng.sgdata.data.database.carparkinfo.CarparkInfoEntity

@Database(
    entities = [CarparkInfoEntity::class, MetadataEntity::class],
    version = 1
)
@TypeConverters(RoomTypeConverters::class)
abstract class SGDataDatabase : RoomDatabase() {

    abstract fun carparkInfoDao(): CarparkInfoDao

    abstract fun metadataDao(): MetadataDao
}