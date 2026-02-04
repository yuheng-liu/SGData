package com.liuyuheng.sgdata.domain.model.carpark

data class CarparkInfo(
    val carparkId: String,
    val address: String,
    val coordinates: Coordinates,
    val carparkType: CarparkType,
    val parkingSystemType: ParkingSystemType,
    val shortTermParkingTiming: String,
    val freeParkingTiming: String,
    val nightParkingAvailable: Boolean,
    val carparkDecks: Int,
    val gantryHeight: Double,
    val carparkBasementAvailable: Boolean,
) {
    data class Coordinates(
        val x: Double,
        val y: Double,
    )
}