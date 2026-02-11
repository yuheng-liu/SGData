package com.liuyuheng.sgdata.presentation.main.navigation

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("Home")
    data object WeatherForecastGraph : AppRoute("WeatherForecastGraph")
    data object WeatherForecastV2Graph : AppRoute("WeatherForecastV2Graph")
    data object CarparkAvailabilityGraph : AppRoute("CarparkAvailabilityGraph")
}