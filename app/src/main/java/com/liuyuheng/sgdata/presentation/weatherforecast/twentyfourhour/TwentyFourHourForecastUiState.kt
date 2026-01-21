package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import java.time.LocalDate

data class TwentyFourHourForecastUiState(
    val selectedDate: LocalDate? = null,
    val twentyFourHourForecast: TwentyFourHourForecastUi = TwentyFourHourForecastUi(),
    val currentDialog: DialogTypes? = null,
)