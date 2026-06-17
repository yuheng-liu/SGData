package com.liuyuheng.sgdata.weatherforecast.presentation.twohour

import com.liuyuheng.sgdata.weatherforecast.domain.model.TwoHourForecast
import java.time.LocalTime

data class TwoHourForecastUiState(
    val selectedTimeslot: LocalTime? = null,
    val twoHourForecast: TwoHourForecast = TwoHourForecast()
)