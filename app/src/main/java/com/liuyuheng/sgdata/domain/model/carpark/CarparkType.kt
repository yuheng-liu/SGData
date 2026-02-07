package com.liuyuheng.sgdata.domain.model.carpark

enum class CarparkType(val value: String, val displayText: String) {
    MULTI_STOREY("MULTI-STOREY CAR PARK", "MULTI-STOREY"),
    SURFACE("SURFACE CAR PARK", "SURFACE"),
    BASEMENT("BASEMENT CAR PARK", "BASEMENT"),
    SURFACE_AND_MULTI_STOREY("SURFACE/MULTI-STOREY CAR PARK", "SURFACE/MULTI-STOREY"),
    COVERED("COVERED CAR PARK", "COVERED"),
    MECHANISED("MECHANISED CAR PARK", "MECHANISED"),
    MECHANISED_AND_SURFACE("MECHANISED AND SURFACE CAR PARK", "MECHANISED AND SURFACE"),
    UNKNOWN("UNKNOWN", "UNKNOWN");

    companion object {
        fun fromValue(value: String) = entries.find { it.value == value } ?: UNKNOWN
    }
}