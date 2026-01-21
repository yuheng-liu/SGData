package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.SGDataSpacer
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.dialog.HttpErrorDialog
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastViewModel
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun TwentyFourHoursForecastScreen(
    viewModel: WeatherForecastViewModel,
) {
    val uiState by viewModel.twentyFourHoursUiState.collectAsStateWithLifecycle()

    when (val currentDialog = uiState.currentDialog) {
        is DialogTypes.HttpError -> HttpErrorDialog(
            errorMessage = currentDialog.message,
            onDismiss = { viewModel.onDismissErrorDialog(WeatherForecastViewModel.WeatherForecastType.TWENTY_FOUR_HOURS) },
        )

        else -> Unit
    }

    if (uiState.twentyFourHourForecast.dateTime == null) {
        ErrorScreen()
    } else {
        TwentyFourHoursForecastScreen(
            uiState = uiState
        )
    }

}

@Composable
fun TwentyFourHoursForecastScreen(
    uiState: TwentyFourHourForecastUiState,
) {
    val forecast = uiState.twentyFourHourForecast
    Column(
        modifier = Modifier.padding(Dimensions.paddingMedium)
    ) {
        Text("24Hrs forecast for ${uiState.selectedDate.toString()}")
        SGDataSpacer()
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(Dimensions.paddingMedium)
            ) {
                Text(
                    text = "General Forecast Details",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = "Temperature: ${forecast.temperature}")
                Text(text = "Relative Humidity: ${forecast.relativeHumidity}")
                Text(text = "Wind: ${forecast.wind}")
                Text(text = "Details: ${forecast.details}")
                Text(text = "Valid Period: ${forecast.validPeriod}")
            }
        }
        SGDataSpacer()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
        ) {
            items(forecast.periodRegionForecasts.size) { index ->
                val periodRegionForecast = forecast.periodRegionForecasts[index]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(Dimensions.paddingMedium)
                    ) {
                        Text(
                            text = "Time Period: ${periodRegionForecast.timePeriod}",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text("West: ${periodRegionForecast.regions.west}")
                        Text("East: ${periodRegionForecast.regions.east}")
                        Text("Central: ${periodRegionForecast.regions.central}")
                        Text("South: ${periodRegionForecast.regions.south}")
                        Text("North: ${periodRegionForecast.regions.north}")
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No data available")
    }
}

@Preview
@Composable
private fun WeatherForecastScreenPreview() {
    BasePreviewComposable {
        TwentyFourHoursForecastScreen(
            uiState = TwentyFourHourForecastUiState(
                selectedDate = LocalDate.now(),
                twentyFourHourForecast = TwentyFourHourForecastUi(
                    dateTime = LocalDateTime.now(),
                    temperature = "25°C/32°C",
                    relativeHumidity = "60% - 95%",
                    wind = "Light; 10-15 km/h",
                    details = "Partly cloudy",
                    validPeriod = "Valid for 24 hours from 12:00 PM today",
                    periodRegionForecasts = listOf(
                        TwentyFourHourForecastUi.PeriodRegionForecastUi(
                            timePeriod = "12:00 PM - 06:00 PM",
                            regions = TwentyFourHourForecastUi.PeriodRegionForecastUi.RegionsUi(
                                west = "Thundery Showers",
                                east = "Partly Cloudy",
                                central = "Showers",
                                south = "Partly Cloudy",
                                north = "Partly Cloudy"
                            )
                        ),
                        TwentyFourHourForecastUi.PeriodRegionForecastUi(
                            timePeriod = "06:00 PM - 12:00 AM",
                            regions = TwentyFourHourForecastUi.PeriodRegionForecastUi.RegionsUi(
                                west = "Partly Cloudy",
                                east = "Partly Cloudy",
                                central = "Partly Cloudy",
                                south = "Partly Cloudy",
                                north = "Partly Cloudy"
                            )
                        )
                    )
                )
            )
        )
    }
}