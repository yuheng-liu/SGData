package com.liuyuheng.sgdata.weatherforecast.presentation.fourday

import com.liuyuheng.sgdata.weatherforecast.domain.model.FourDayForecast
import java.time.LocalDate

data class FourDayForecastUiState(
    val selectedDate: LocalDate = LocalDate.now(),
    val fourDayForecast: FourDayForecast = FourDayForecast(),
)