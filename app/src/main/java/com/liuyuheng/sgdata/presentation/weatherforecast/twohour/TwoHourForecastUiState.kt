package com.liuyuheng.sgdata.presentation.weatherforecast.twohour

import com.liuyuheng.sgdata.domain.model.TwoHourForecast
import java.time.LocalTime

data class TwoHourForecastUiState(
    val selectedTimeslot: LocalTime? = null,
    val twoHourForecast: TwoHourForecast = TwoHourForecast()
)