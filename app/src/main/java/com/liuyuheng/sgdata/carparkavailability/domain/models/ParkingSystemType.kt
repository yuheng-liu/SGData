package com.liuyuheng.sgdata.carparkavailability.domain.models

/**
 * Retrieved From https://data.gov.sg/datasets/d_23f946fa557947f93a8043bbef41dd09/view
 */
enum class ParkingSystemType(val value: String, val displayText: String) {
    ELECTRONIC("ELECTRONIC PARKING", "ELECTRONIC"),
    COUPON("COUPON PARKING", "COUPON"),
    UNKNOWN("UNKNOWN", "UNKNOWN");

    companion object {
        fun fromValue(value: String) = entries.find { it.value == value } ?: UNKNOWN
    }
}