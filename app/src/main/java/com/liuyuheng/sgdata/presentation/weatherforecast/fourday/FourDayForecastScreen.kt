package com.liuyuheng.sgdata.presentation.weatherforecast.fourday

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.domain.model.weather.FourDayForecast
import com.liuyuheng.sgdata.domain.model.weather.shared.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import com.liuyuheng.sgdata.domain.model.weather.shared.Wind
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.SGDataSpacer
import com.liuyuheng.sgdata.presentation.shared.datepickertextfield.DatePickerTextField
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.dialog.HttpErrorDialog
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastViewModel
import com.liuyuheng.sgdata.presentation.weatherforecast.shared.Constants
import com.liuyuheng.sgdata.presentation.weatherforecast.shared.GeneralWeatherCard
import com.liuyuheng.sgdata.shared.toEpochMillis
import com.liuyuheng.sgdata.shared.toLocalDate
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun FourDayForecastScreen(
    viewModel: WeatherForecastViewModel
) {
    val uiState by viewModel.fourDaysForecastUiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    when (val currentDialog = dialogState) {
        is DialogTypes.HttpError -> HttpErrorDialog(
            errorMessage = currentDialog.message,
            onDismiss = { viewModel.onDismissErrorDialog() },
        )

        else -> Unit
    }

    FourDayForecastScreen(
        uiState = uiState,
        onDateSelected = { selectedDate ->
            viewModel.setSelectedDate(selectedDate)
        }
    )
}

@Composable
private fun FourDayForecastScreen(
    uiState: FourDayForecastUiState,
    onDateSelected: (LocalDate?) -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimensions.paddingMedium)
    ) {
        SGDataSpacer()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimensions.paddingLarge)
        ) {
            Text(
                text = "From: "
            )
            DatePickerTextField(
                initialSelectedDateMillis = uiState.selectedDate.toEpochMillis()
            ) { millis ->
                onDateSelected(millis?.toLocalDate())
            }
        }
        SGDataSpacer()
        FourDayForecastList(uiState.fourDayForecast)
    }
}

@Composable
private fun FourDayForecastList(
    fourDayForecast: FourDayForecast,
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
    ) {
        fourDayForecast.forecastsList.forEach { forecast ->
            GeneralWeatherCard(
                dateTime = forecast.date,
                temperature = forecast.temperature,
                relativeHumidity = forecast.relativeHumidity,
                wind = forecast.wind,
                weatherText = forecast.details.text
            )
        }
    }
}

@Preview
@Composable
private fun WeatherForecastScreenPreview() {
    BasePreviewComposable {
        FourDayForecastScreen(
            uiState = FourDayForecastUiState(
                fourDayForecast = FourDayForecast(
                    startDate = LocalDateTime.now(),
                    forecastsList = listOf(
                        FourDayForecast.Forecast(
                            date = LocalDateTime.now(),
                            dayOfWeek = DayOfWeek.MONDAY,
                            temperature = Temperature(25, 33, Constants.UNIT_DEGREE_CELSIUS),
                            relativeHumidity = RelativeHumidity(60, 95, "%"),
                            wind = Wind(
                                Wind.WindSpeed(10, 20),
                                direction = "SW",
                            ),
                            details = FourDayForecast.Forecast.ForecastDetails(
                                summary = "",
                                code = "",
                                text = WeatherText.LIGHT_RAIN
                            ),
                        ),
                        FourDayForecast.Forecast(
                            date = LocalDateTime.now().plusDays(1),
                            dayOfWeek = DayOfWeek.TUESDAY,
                            temperature = Temperature(26, 32, Constants.UNIT_DEGREE_CELSIUS),
                            relativeHumidity = RelativeHumidity(65, 90, "%"),
                            wind = Wind(
                                Wind.WindSpeed(12, 22),
                                direction = "NS",
                            ),
                            details = FourDayForecast.Forecast.ForecastDetails(
                                summary = "",
                                code = "",
                                text = WeatherText.FAIR_DAY
                            ),
                        )
                    )
                )
            ),
            onDateSelected = {},
        )
    }
}