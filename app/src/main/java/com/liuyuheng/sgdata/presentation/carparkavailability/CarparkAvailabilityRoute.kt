package com.liuyuheng.sgdata.presentation.carparkavailability

sealed class CarparkAvailabilityRoute(val route: String) {
    data object CarparkInfo : CarparkAvailabilityRoute("CarparkInfo")
}