package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

sealed class TwentyFourHourForecastUiState {
    data object Idle : TwentyFourHourForecastUiState()
    data object Loading : TwentyFourHourForecastUiState()
    data class Loaded(
        val twentyFourHourForecast: TwentyFourHourForecastUi = TwentyFourHourForecastUi(),
    ) : TwentyFourHourForecastUiState()

    data class Error(
        val errorMessage: String,
    ) : TwentyFourHourForecastUiState()
}