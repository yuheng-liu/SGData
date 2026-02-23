package com.liuyuheng.sgdata.presentation.weatherforecast

sealed class WeatherForecastRoute(val route: String) {
    data object FourDayForecast : WeatherForecastRoute("FourDayForecast")
    data object TwentyFourHourForecast : WeatherForecastRoute("TwentyFourHourForecast")
    data object TwoHourForecast : WeatherForecastRoute("TwoHourForecast")

    data object V2TwentyFourHourForecast : WeatherForecastRoute("V2TwentyFourHourForecast")
    data object V2TwoHourForecast : WeatherForecastRoute("V2TwoHourForecast")
    data object V2FourDayForecast : WeatherForecastRoute("V2FourDayForecast")
}