package com.liuyuheng.sgdata.data.database

import androidx.room.TypeConverter
import com.liuyuheng.sgdata.domain.model.carpark.CarparkType
import com.liuyuheng.sgdata.domain.model.carpark.ParkingSystemType

class RoomTypeConverters {

    @TypeConverter
    fun fromCarparkType(type: CarparkType): String = type.value

    @TypeConverter
    fun toCarparkType(value: String): CarparkType =
        CarparkType.entries.find { it.value == value } ?: CarparkType.UNKNOWN

    @TypeConverter
    fun fromParkingSystemType(type: ParkingSystemType): String = type.value

    @TypeConverter
    fun toParkingSystemType(value: String): ParkingSystemType =
        ParkingSystemType.entries.find { it.value == value } ?: ParkingSystemType.UNKNOWN
}