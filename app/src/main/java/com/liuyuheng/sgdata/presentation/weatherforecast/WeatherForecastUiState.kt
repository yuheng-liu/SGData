package com.liuyuheng.sgdata.presentation.weatherforecast

import com.liuyuheng.sgdata.domain.model.WeatherForecast
import java.time.LocalDate

data class WeatherForecastUiState(
    val selectedDate: LocalDate? = null,
    val weatherForecast: WeatherForecast = WeatherForecast(),
)