package com.liuyuheng.sgdata.presentation.weatherforecast.fourday

import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import java.time.LocalDate

data class FourDayForecastUiState(
    val selectedDate: LocalDate? = LocalDate.now(),
    val fourDayForecast: FourDayForecastUi = FourDayForecastUi(),
    val selectedForecast: FourDayForecastUi.ForecastUi? = null,
    val currentDialog: DialogTypes? = null,
)