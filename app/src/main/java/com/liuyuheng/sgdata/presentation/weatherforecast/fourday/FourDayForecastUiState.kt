package com.liuyuheng.sgdata.presentation.weatherforecast.fourday

import java.time.LocalDate

data class FourDayForecastUiState(
    val selectedDate: LocalDate? = LocalDate.now(),
    val fourDayForecast: FourDayForecastUi = FourDayForecastUi(),
)