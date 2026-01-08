package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.presentation.components.DatePickerTextField
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.SGDataSpacer
import com.liuyuheng.sgdata.presentation.shared.utils.toLocalDate
import java.time.LocalDate

@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WeatherForecastScreen(
        uiState = uiState,
        onDateSelected = viewModel::setSelectedDate,
        onButtonClicked = viewModel::loadWeatherForecasts,
    )
}

@Composable
fun WeatherForecastScreen(
    uiState: WeatherForecastUiState,
    onDateSelected: (LocalDate?) -> Unit,
    onButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimensions.paddingMedium)
    ) {
        DatePickerTextField { millis ->
            onDateSelected(millis?.toLocalDate())
        }
        SGDataSpacer()
        Button(
            onClick = { onButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Get Forecast")
        }
        SGDataSpacer()
        uiState.weatherForecast.forecastsMap.forEach { (date, forecasts) ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(forecasts.size) { index ->
                    val forecast = forecasts[index]
                    Text(text = "forecast: ${forecast.temperature}")
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherForecastScreenPreview() {
    BasePreviewComposable {
        WeatherForecastScreen(
            uiState = WeatherForecastUiState(
                selectedDate = LocalDate.of(2026, 1, 8),

                ),
            onDateSelected = {},
            onButtonClicked = {},
        )
    }
}