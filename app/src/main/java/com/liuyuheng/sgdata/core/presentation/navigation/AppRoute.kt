package com.liuyuheng.sgdata.core.presentation.navigation

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("Home")
    data object WeatherForecastGraph : AppRoute("WeatherForecastGraph")
    data object CarparkAvailabilityGraph : AppRoute("CarparkAvailabilityGraph")
}