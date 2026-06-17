package com.liuyuheng.sgdata.carparkavailability.domain.models

enum class ParkingSystemType(val value: String, val displayText: String) {
    ELECTRONIC("ELECTRONIC PARKING", "ELECTRONIC"),
    COUPON("COUPON PARKING", "COUPON"),
    UNKNOWN("UNKNOWN", "UNKNOWN");

    companion object {
        fun fromValue(value: String) = entries.find { it.value == value } ?: UNKNOWN
    }
}