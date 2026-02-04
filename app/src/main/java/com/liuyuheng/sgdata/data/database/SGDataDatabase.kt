package com.liuyuheng.sgdata.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [CarparkInfoEntity::class],
    version = 1
)
@TypeConverters(RoomTypeConverters::class)
abstract class SGDataDatabase : RoomDatabase() {

    abstract fun carparkInfoDao(): CarparkInfoDao
}