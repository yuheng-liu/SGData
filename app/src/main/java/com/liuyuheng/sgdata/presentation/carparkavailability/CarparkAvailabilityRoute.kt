package com.liuyuheng.sgdata.presentation.carparkavailability

sealed class CarparkAvailabilityRoute(val route: String) {
    data object CarparkAvailability : CarparkAvailabilityRoute("CarparkAvailability")
    data object CarparkInfo : CarparkAvailabilityRoute("CarparkInfo")
}