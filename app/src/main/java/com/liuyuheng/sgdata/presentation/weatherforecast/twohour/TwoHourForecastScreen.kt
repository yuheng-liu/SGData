package com.liuyuheng.sgdata.presentation.weatherforecast.twohour

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.domain.model.weather.TwoHourForecast
import com.liuyuheng.sgdata.domain.model.weather.TwoHourForecast.AreaForecast
import com.liuyuheng.sgdata.domain.model.weather.shared.TimePeriod
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.SGDataSpacer
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.dialog.HttpErrorDialog
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastViewModel
import com.liuyuheng.sgdata.presentation.weatherforecast.shared.WeatherTextCard
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
        uiState = uiState
    )
}

@Composable
private fun TwoHourForecastScreen(
    uiState: TwoHourForecastUiState,
) {
    Column(
        modifier = Modifier
            .padding(all = Dimensions.paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SGDataSpacer()
        Text(uiState.twoHourForecast.timePeriod.text)
        SGDataSpacer()
        TwoHourForecastList(uiState.twoHourForecast.areaForecasts)
    }
}

@Composable
private fun TwoHourForecastList(
    areaForecastList: List<AreaForecast>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
    ) {
        items(areaForecastList.size) { index ->
            val areaForecast = areaForecastList[index]
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                WeatherTextCard(
                    text = areaForecast.area,
                    weatherText = areaForecast.forecast
                )
            }
        }
    }
}

@Preview
@Composable
private fun TwoHourForecastScreen_Preview() {
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
                        AreaForecast(
                            area = "Ang Mo Kio",
                            forecast = WeatherText.WINDY
                        ),
                        AreaForecast(
                            area = "Bishan",
                            forecast = WeatherText.FAIR
                        ),
                        AreaForecast(
                            area = "City",
                            forecast = WeatherText.CLOUDY
                        )
                    )
                )
            )
        )
    }
}