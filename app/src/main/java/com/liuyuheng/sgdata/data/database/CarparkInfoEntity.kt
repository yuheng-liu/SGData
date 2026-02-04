package com.liuyuheng.sgdata.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.liuyuheng.sgdata.domain.model.carpark.CarparkType
import com.liuyuheng.sgdata.domain.model.carpark.ParkingSystemType

@Entity(tableName = "carpark_info")
data class CarparkInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val carparkNumber: String,
    val address: String,
    val xCoordinate: Double,
    val yCoordinate: Double,
    val carparkType: CarparkType,
    val typeOfParkingSystem: ParkingSystemType,
    val shortTermParking: String,
    val freeParking: String,
    val nightParking: Boolean,
    val carparkDecks: Int,
    val gantryHeight: Double,
    val carparkBasement: Boolean,
)