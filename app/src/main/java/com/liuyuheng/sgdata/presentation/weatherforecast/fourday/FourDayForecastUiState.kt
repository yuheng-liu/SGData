package com.liuyuheng.sgdata.presentation.weatherforecast.fourday

import com.liuyuheng.sgdata.domain.model.weather.FourDayForecast
import java.time.LocalDate

data class FourDayForecastUiState(
    val selectedDate: LocalDate = LocalDate.now(),
    val fourDayForecast: FourDayForecast = FourDayForecast(),
)