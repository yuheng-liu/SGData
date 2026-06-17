package com.liuyuheng.sgdata.carparkavailability.data.mappers

import com.liuyuheng.sgdata.carparkavailability.data.models.CarparkInfoEntity
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo.Coordinates

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