package com.liuyuheng.sgdata.data.model.mappers

import com.liuyuheng.sgdata.data.database.CarparkInfoEntity
import com.liuyuheng.sgdata.data.model.CarparkInfoDto
import com.liuyuheng.sgdata.data.toBooleanOrNull
import com.liuyuheng.sgdata.data.toEnumOrNull
import com.liuyuheng.sgdata.domain.model.CarparkInfo
import com.liuyuheng.sgdata.domain.model.CarparkInfo.CarparkType
import com.liuyuheng.sgdata.domain.model.CarparkInfo.Coordinates
import com.liuyuheng.sgdata.domain.model.CarparkInfo.ParkingSystemType

fun CarparkInfoDto.toEntity() = CarparkInfoEntity(
    id = id,
    carparkNumber = carparkNumber,
    address = address,
    xCoordinate = xCoordinate,
    yCoordinate = yCoordinate,
    carparkType = carparkType,
    typeOfParkingSystem = typeOfParkingSystem,
    shortTermParking = shortTermParking,
    freeParking = freeParking,
    nightParking = nightParking,
    carparkDecks = carparkDecks,
    gantryHeight = gantryHeight,
    carparkBasement = carparkBasement
)

fun CarparkInfoEntity.toDomain() = CarparkInfo(
    carparkId = carparkNumber,
    address = address,
    coordinates = Coordinates(
        x = xCoordinate.toDouble(),
        y = yCoordinate.toDouble()
    ),
    carparkType = carparkType.toEnumOrNull<CarparkType>() ?: CarparkType.UNKNOWN,
    parkingSystemType = typeOfParkingSystem.toEnumOrNull<ParkingSystemType>() ?: ParkingSystemType.UNKNOWN,
    shortTermParkingTiming = shortTermParking,
    freeParkingTiming = freeParking,
    nightParking = nightParking.toBooleanOrNull() ?: false,
    carparkDecks = carparkDecks.toIntOrNull() ?: 0,
    gantryHeight = gantryHeight.toDoubleOrNull() ?: 0.0,
    carparkBasement = carparkBasement.toBooleanOrNull() ?: false
)