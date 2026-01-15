package com.liuyuheng.sgdata.presentation.weatherforecast.navigation

sealed class WeatherForecastRoute(val route: String) {
    data object FourDayForecast : WeatherForecastRoute("FourDayForecast")
    data object TwentyFourHourForecast : WeatherForecastRoute("TwentyFourHourForecast")
}