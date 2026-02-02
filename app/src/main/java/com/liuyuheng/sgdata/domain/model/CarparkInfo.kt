package com.liuyuheng.sgdata.domain.model

data class CarparkInfo(
    val carparkId: String,
    val address: String,
    val coordinates: Coordinates,
    val carparkType: CarparkType,
    val parkingSystemType: ParkingSystemType,
    val shortTermParkingTiming: String,
    val freeParkingTiming: String,
    val nightParking: Boolean,
    val carparkDecks: Int,
    val gantryHeight: Double,
    val carparkBasement: Boolean,
) {
    data class Coordinates(
        val x: Double,
        val y: Double,
    )

    enum class CarparkType(val value: String) {
        MULTI_STOREY("MULTI_STOREY CAR PARK"),
        SURFACE("SURFACE CAR PARK"),
        BASEMENT("BASEMENT CAR PARK"),
        SURFACE_AND_MULTI_STOREY("SURFACE/MULTI-STOREY CAR PARK"),
        COVERED("COVERED CAR PARK"),
        MECHANISED("MECHANISED CAR PARK"),
        MECHANISED_AND_SURFACE("MECHANISED AND SURFACE CAR PARK"),
        UNKNOWN("UNKNOWN")
    }

    enum class ParkingSystemType(val value: String) {
        ELECTRONIC("ELECTRONIC PARKING"),
        COUPON("COUPON PARKING"),
        UNKNOWN("UNKNOWN")
    }
}