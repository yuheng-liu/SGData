package com.liuyuheng.sgdata.domain.model.carpark

enum class ParkingSystemType(val value: String) {
    ELECTRONIC("ELECTRONIC PARKING"),
    COUPON("COUPON PARKING"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun fromValue(value: String) = entries.find { it.value == value } ?: UNKNOWN
    }
}