package com.liuyuheng.sgdata.carparkavailability.presentation

sealed class CarparkAvailabilityRoute(val route: String) {
    data object CarparkAvailability : CarparkAvailabilityRoute("CarparkAvailability")
    data object CarparkInfo : CarparkAvailabilityRoute("CarparkInfo")
}