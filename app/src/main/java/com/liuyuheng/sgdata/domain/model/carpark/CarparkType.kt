package com.liuyuheng.sgdata.domain.model.carpark

enum class CarparkType(val value: String) {
    MULTI_STOREY("MULTI-STOREY CAR PARK"),
    SURFACE("SURFACE CAR PARK"),
    BASEMENT("BASEMENT CAR PARK"),
    SURFACE_AND_MULTI_STOREY("SURFACE/MULTI-STOREY CAR PARK"),
    COVERED("COVERED CAR PARK"),
    MECHANISED("MECHANISED CAR PARK"),
    MECHANISED_AND_SURFACE("MECHANISED AND SURFACE CAR PARK"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun fromValue(value: String) = entries.find { it.value == value } ?: UNKNOWN
    }
}