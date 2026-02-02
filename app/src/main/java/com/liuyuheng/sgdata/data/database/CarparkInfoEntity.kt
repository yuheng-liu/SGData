package com.liuyuheng.sgdata.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carpark_info")
data class CarparkInfoEntity(
    @PrimaryKey val id: Int,
    val carparkNumber: String,
    val address: String,
    val xCoordinate: String,
    val yCoordinate: String,
    val carparkType: String,
    val typeOfParkingSystem: String,
    val shortTermParking: String,
    val freeParking: String,
    val nightParking: String,
    val carparkDecks: String,
    val gantryHeight: String,
    val carparkBasement: String,
)