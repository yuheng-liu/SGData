package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

sealed class TwentyFourHourForecastV2UiState {
    data object Idle : TwentyFourHourForecastV2UiState()
    data object Loading : TwentyFourHourForecastV2UiState()
    data class Loaded(
        val twentyFourHourForecast: TwentyFourHourForecastUi = TwentyFourHourForecastUi(),
    ) : TwentyFourHourForecastV2UiState()

    data class Error(
        val errorMessage: String,
    ) : TwentyFourHourForecastV2UiState()
}