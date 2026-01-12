package com.liuyuheng.sgdata.presentation.weatherforecast.model

import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import java.time.LocalDate

data class WeatherForecastUiState(
    val selectedDate: LocalDate? = null,
    val weatherForecast: WeatherForecastUi = WeatherForecastUi(),
    val currentDialog: DialogTypes? = null,
)