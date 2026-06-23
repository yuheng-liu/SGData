package com.liuyuheng.sgdata.carparkavailability.domain.models

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
    val lotsAvailability: List<LotsAvailability>? = null,
) {
    data class Coordinates(
        val x: Double,
        val y: Double,
    )

    data class LotsAvailability(
        val totalLots: Int,
        val lotType: CarparkLotType,
        val availableLots: Int,
    )
}