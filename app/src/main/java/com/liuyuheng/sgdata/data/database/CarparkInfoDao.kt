package com.liuyuheng.sgdata.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CarparkInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CarparkInfoEntity>)

    @Query("SELECT * FROM carpark_info")
    fun getCarparkInfoStream(): Flow<List<CarparkInfoEntity>>

    @Query("DELETE FROM carpark_info")
    suspend fun clearCarparkInfo()

}