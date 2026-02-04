package com.liuyuheng.sgdata.data.model.mappers

import com.liuyuheng.sgdata.data.database.CarparkInfoEntity
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo.Coordinates

fun CarparkInfoEntity.toDomain() = CarparkInfo(
    carparkId = carparkNumber,
    address = address,
    coordinates = Coordinates(xCoordinate, yCoordinate),
    carparkType = carparkType,
    parkingSystemType = typeOfParkingSystem,
    shortTermParkingTiming = shortTermParking,
    freeParkingTiming = freeParking,
    nightParkingAvailable = nightParking,
    carparkDecks = carparkDecks,
    gantryHeight = gantryHeight,
    carparkBasementAvailable = carparkBasement
)