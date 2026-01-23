package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import java.time.LocalDate

data class TwentyFourHourForecastUiState(
    val selectedDate: LocalDate? = null,
    val twentyFourHourForecast: TwentyFourHourForecastUi = TwentyFourHourForecastUi(),
)