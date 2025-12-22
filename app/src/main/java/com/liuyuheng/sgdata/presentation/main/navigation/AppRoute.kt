package com.liuyuheng.sgdata.presentation.main.navigation

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("Home")
    data object WeatherForecast : AppRoute("WeatherForecast")
}