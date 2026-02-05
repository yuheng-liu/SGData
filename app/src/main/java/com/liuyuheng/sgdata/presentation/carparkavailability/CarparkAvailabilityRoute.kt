package com.liuyuheng.sgdata.presentation.carparkavailability

sealed class CarparkAvailabilityRoute(val route: String) {
    data object CarparkAvailability : CarparkAvailabilityRoute("CarparkInfo")
    data object CarparkInfo : CarparkAvailabilityRoute("CarparkInfo")
}