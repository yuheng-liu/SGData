package com.liuyuheng.sgdata.presentation.weatherforecast

sealed class WeatherForecastRoute(val route: String) {
    data object FourDayForecast : WeatherForecastRoute("FourDayForecast")
    data object TwentyFourHourForecast : WeatherForecastRoute("TwentyFourHourForecast")
    data object TwoHourForecast : WeatherForecastRoute("TwoHourForecast")
}