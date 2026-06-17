package com.liuyuheng.sgdata.weatherforecast.presentation.twentyfourhour

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