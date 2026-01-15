package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhours

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastViewModel

@Composable
fun TwentyFourHoursForecastScreen(
    viewModel: WeatherForecastViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(uiState.selectedForecast?.date ?: "")
    }
}