package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.liuyuheng.sgdata.presentation.weatherforecast.model.WeatherForecastUi
import com.liuyuheng.sgdata.presentation.weatherforecast.model.WeatherForecastUiState
import com.liuyuheng.sgdata.utils.toLocalDate
import java.time.LocalDate

@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WeatherForecastScreen(
        uiState = uiState,
        initialSelectedDateMillis = viewModel.todayMillis,
        onDateSelected = viewModel::setSelectedDate,
        onButtonClicked = viewModel::loadWeatherForecasts,
    )
}

@Composable
fun WeatherForecastScreen(
    uiState: WeatherForecastUiState,
    initialSelectedDateMillis: Long?,
    onDateSelected: (LocalDate?) -> Unit,
    onButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimensions.paddingMedium)
    ) {
        DatePickerTextField(
            initialSelectedDateMillis = initialSelectedDateMillis
        ) { millis ->
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
        ForecastList(uiState.weatherForecast.forecastsList)
    }
}

@Composable
private fun ForecastList(
    forecastList: List<WeatherForecastUi.ForecastUi>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
    ) {
        items(forecastList.size) { index ->
            val forecast = forecastList[index]
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(Dimensions.paddingMedium)
                ) {
                    Text(text = "Date: ${forecast.date}")
                    Text(text = "Day Of Week: ${forecast.dayOfWeek}")
                    Text(text = "Temperature: ${forecast.temperature}")
                    Text(text = "Relative Humidity: ${forecast.relativeHumidity}")
                    Text(text = "Wind: ${forecast.wind}")
                    Text(text = "Details: ${forecast.details}")
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
                weatherForecast = WeatherForecastUi(
                    forecastsList = listOf(
                        WeatherForecastUi.ForecastUi(
                            date = "2026-01-01",
                            dayOfWeek = "Monday",
                            temperature = "24°C/33°C",
                            relativeHumidity = "55/85%",
                            wind = "15/25 NE",
                            details = "FA: Fair Day, Fair",
                        ),
                        WeatherForecastUi.ForecastUi(
                            date = "2026-01-02",
                            dayOfWeek = "Tuesday",
                            temperature = "22°C/30°C",
                            relativeHumidity = "50/80%",
                            wind = "15/25 NE",
                            details = "FA: Fair Day, Fair",
                        )
                    )
                )
            ),
            initialSelectedDateMillis = null,
            onDateSelected = {},
            onButtonClicked = {},
        )
    }
}