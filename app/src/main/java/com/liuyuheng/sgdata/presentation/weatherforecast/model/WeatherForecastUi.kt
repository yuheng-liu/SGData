package com.liuyuheng.sgdata.presentation.weatherforecast.model

data class WeatherForecastUi(
    val forecastsList: List<ForecastUi> = emptyList(),
) {
    data class ForecastUi(
        val date: String,
        val dayOfWeek: String,
        val temperature: String,
        val relativeHumidity: String,
        val wind: String,
        val details: String,
    )
}
