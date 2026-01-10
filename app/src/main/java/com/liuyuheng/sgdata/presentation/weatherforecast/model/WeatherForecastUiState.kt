package com.liuyuheng.sgdata.presentation.weatherforecast.model

import java.time.LocalDate

data class WeatherForecastUiState(
    val selectedDate: LocalDate? = null,
    val weatherForecast: WeatherForecastUi = WeatherForecastUi(),
)