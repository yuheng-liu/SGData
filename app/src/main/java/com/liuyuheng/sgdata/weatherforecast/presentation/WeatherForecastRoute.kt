package com.liuyuheng.sgdata.weatherforecast.presentation

sealed class WeatherForecastRoute(val route: String) {
    data object FourDayForecast : WeatherForecastRoute("FourDayForecast")
    data object TwentyFourHourForecast : WeatherForecastRoute("TwentyFourHourForecast")
    data object TwoHourForecast : WeatherForecastRoute("TwoHourForecast")
}