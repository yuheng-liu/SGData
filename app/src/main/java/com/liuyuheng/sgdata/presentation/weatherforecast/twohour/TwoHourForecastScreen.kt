package com.liuyuheng.sgdata.presentation.weatherforecast.twohour

import androidx.compose.foundation.layout.Arrangement
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.domain.model.TwoHourForecast
import com.liuyuheng.sgdata.domain.model.weather.TimePeriod
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.SGDataSpacer
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.dialog.HttpErrorDialog
import com.liuyuheng.sgdata.presentation.shared.timeslotdropdown.TimeSlotDropdown
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastViewModel
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun TwoHourForecastScreen(
    viewModel: WeatherForecastViewModel,
) {
    val uiState by viewModel.twoHoursForecastUiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    when (val currentDialog = dialogState) {
        is DialogTypes.HttpError -> HttpErrorDialog(
            errorMessage = currentDialog.message,
            onDismiss = { viewModel.onDismissErrorDialog() },
        )

        else -> Unit
    }

    TwoHourForecastScreen(
        uiState = uiState,
        onTimeSlotSelected = viewModel::onTwoHourTimeslotSelected,
        onGetForecastButtonPressed = viewModel::fetchTwoHourForecast
    )
}

@Composable
private fun TwoHourForecastScreen(
    uiState: TwoHourForecastUiState,
    onTimeSlotSelected: (LocalTime) -> Unit,
    onGetForecastButtonPressed: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimensions.paddingMedium)
    ) {
        Text("Select a 2hr timeslot to retrieve the forecast")
        SGDataSpacer()
        TimeSlotDropdown { localTime ->
            onTimeSlotSelected(localTime)
        }
        SGDataSpacer()
        Button(
            onClick = {
                if (uiState.selectedTimeslot != null) {
                    onGetForecastButtonPressed()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get Forecast")
        }
        SGDataSpacer()
        if (uiState.twoHourForecast.timePeriod.text.isNotEmpty()) {
            Text("Below are the forecasts for the time slot ${uiState.twoHourForecast.timePeriod.text}")
        }
        SGDataSpacer()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
        ) {
            items(uiState.twoHourForecast.areaForecasts.size) { index ->
                val areaForecast = uiState.twoHourForecast.areaForecasts[index]
                Text(text = "${areaForecast.area}: ${areaForecast.forecast}")
            }
        }
    }
}

@Preview
@Composable
private fun TwoHourForecastScreenPreview() {
    BasePreviewComposable {
        TwoHourForecastScreen(
            uiState = TwoHourForecastUiState(
                selectedTimeslot = LocalTime.of(12, 0),
                twoHourForecast = TwoHourForecast(
                    startTime = LocalDateTime.now(),
                    updatedTimestamp = LocalDateTime.now(),
                    timePeriod = TimePeriod(
                        start = "16:00",
                        end = "18:00",
                        text = "4.00 pm to 6.00 pm",
                    ),
                    areaForecasts = listOf(
                        TwoHourForecast.AreaForecast(
                            area = "Ang Mo Kio",
                            forecast = "Windy"
                        ),
                        TwoHourForecast.AreaForecast(
                            area = "Bishan",
                            forecast = "Sunny"
                        ),
                        TwoHourForecast.AreaForecast(
                            area = "City",
                            forecast = "Cloudy"
                        )
                    )
                )
            ),
            onTimeSlotSelected = {},
            onGetForecastButtonPressed = {}
        )
    }
}