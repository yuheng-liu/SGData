package com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.R
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.SGDataSpacer
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.dialog.HttpErrorDialog
import com.liuyuheng.sgdata.presentation.shared.error.ErrorBox
import com.liuyuheng.sgdata.presentation.weatherforecast.WeatherForecastV2ViewModel
import com.liuyuheng.sgdata.presentation.weatherforecast.shared.WeatherRegion
import com.liuyuheng.sgdata.presentation.weatherforecast.shared.WeatherTextCard
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.TwentyFourHourForecastUi.PeriodRegionForecastUi
import com.liuyuheng.sgdata.shared.toDisplayDateV2
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun TwentyFourHourForecastV2Screen(
    viewModel: WeatherForecastV2ViewModel,
    onNavigateToTwoHourForecast: () -> Unit,
    onNavigateToFourDayForecast: () -> Unit
) {
    val uiState by viewModel.twentyFourHourForecastUiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    when (val currentDialog = dialogState) {
        is DialogTypes.HttpError -> HttpErrorDialog(
            errorMessage = currentDialog.message,
            onDismiss = { viewModel.onDismissErrorDialog() },
        )

        else -> Unit
    }

    if (uiState.twentyFourHourForecast.dateTime == null) {
        ErrorBox("No data available")
    } else {
        TwentyFourHoursForecastV2Screen(
            uiState = uiState,
            onNavigateToTwoHourForecast = onNavigateToTwoHourForecast,
            onNavigateToFourDayForecast = onNavigateToFourDayForecast,
            onWeatherRegionClicked = viewModel::onWeatherRegionClicked
        )
    }
}

@Composable
private fun TwentyFourHoursForecastV2Screen(
    uiState: TwentyFourHourForecastUiState,
    onNavigateToTwoHourForecast: () -> Unit,
    onNavigateToFourDayForecast: () -> Unit,
    onWeatherRegionClicked: (WeatherRegion) -> Unit
) {
    val forecast = uiState.twentyFourHourForecast

    Column(
        modifier = Modifier
            .padding(
                top = Dimensions.paddingLarge,
                start = Dimensions.paddingMedium,
                end = Dimensions.paddingMedium,
                bottom = Dimensions.paddingSmall
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "24 hrs Forecast: Singapore",
            style = MaterialTheme.typography.headlineMedium
        )
        SGDataSpacer()
        GeneralForecastCard(forecast)
        if (uiState.twentyFourHourForecast.periodRegionForecasts.isNotEmpty()) {
            PeriodRegionForecastSection(
                periodRegionForecastList = uiState.twentyFourHourForecast.periodRegionForecasts,
                onCardClicked = { weatherRegion ->
                    onWeatherRegionClicked(weatherRegion)
                    onNavigateToTwoHourForecast()
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToFourDayForecast() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text("See 4-day Weather Range")
            IconButton({}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_circle_right),
                    null
                )
            }
        }
    }
}

@Composable
private fun GeneralForecastCard(
    uiState: TwentyFourHourForecastUi,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimensions.cardRowHeight)
                .padding(horizontal = Dimensions.paddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(uiState.dateTime?.toDisplayDateV2()?.replaceFirst(" ", "\n") ?: "")
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row {
                    Image(
                        modifier = Modifier.size(Dimensions.iconSizeMedium),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_highest_temp),
                        contentDescription = null
                    )
                    Text("${uiState.temperature.high}${uiState.temperature.unit}")
                }
                Row {
                    Image(
                        modifier = Modifier.size(Dimensions.iconSizeMedium),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_lowest_temp),
                        contentDescription = null
                    )
                    Text("${uiState.temperature.low}${uiState.temperature.unit}")
                }
            }
            Column {
                Row {
                    Image(
                        modifier = Modifier.size(Dimensions.iconSizeMedium),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_water_droplet),
                        contentDescription = null
                    )
                    Text(uiState.relativeHumidity)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(Dimensions.iconSizeMedium),
                        imageVector = ImageVector.vectorResource(R.drawable.image_wind),
                        contentDescription = null
                    )
                    Text(uiState.wind.replaceFirst(" ", "\n"))
                }
            }
            Image(
                modifier = Modifier.size(Dimensions.imageSizeSmall),
                imageVector = ImageVector.vectorResource(R.drawable.image_heavy_rain),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ColumnScope.PeriodRegionForecastSection(
    periodRegionForecastList: List<PeriodRegionForecastUi>,
    onCardClicked: (WeatherRegion) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val periodRegionForecast = periodRegionForecastList[selectedIndex]

    Column(
        modifier = Modifier.weight(1f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (selectedIndex != 0) {
                IconButton({ selectedIndex-- }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_circle_left),
                        null
                    )
                }
            }
            Text(periodRegionForecast.timePeriod)
            if (selectedIndex != periodRegionForecastList.lastIndex) {
                IconButton({ selectedIndex++ }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_circle_right),
                        null
                    )
                }
            }
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            WeatherTextCard(
                text = WeatherRegion.CENTRAL.text,
                weatherText = periodRegionForecast.regions.central,
                onClick = { onCardClicked(WeatherRegion.CENTRAL) }
            )
            SGDataSpacer(Dimensions.paddingLarge)
            WeatherTextCard(
                text = WeatherRegion.EAST.text,
                weatherText = periodRegionForecast.regions.east,
                onClick = { onCardClicked(WeatherRegion.EAST) }
            )
            SGDataSpacer(Dimensions.paddingLarge)
            WeatherTextCard(
                text = WeatherRegion.NORTH.text,
                weatherText = periodRegionForecast.regions.north,
                onClick = { onCardClicked(WeatherRegion.NORTH) }
            )
            SGDataSpacer(Dimensions.paddingLarge)
            WeatherTextCard(
                text = WeatherRegion.SOUTH.text,
                weatherText = periodRegionForecast.regions.south,
                onClick = { onCardClicked(WeatherRegion.SOUTH) }
            )
            SGDataSpacer(Dimensions.paddingLarge)
            WeatherTextCard(
                text = WeatherRegion.WEST.text,
                weatherText = periodRegionForecast.regions.west,
                onClick = { onCardClicked(WeatherRegion.WEST) }
            )
        }
    }
}

@Preview
@Composable
private fun TwentyFourHoursForecastV2ScreenPreview() {
    BasePreviewComposable {
        TwentyFourHoursForecastV2Screen(
            uiState = TwentyFourHourForecastUiState(
                selectedDate = LocalDate.now(),
                twentyFourHourForecast = TwentyFourHourForecastUi(
                    dateTime = LocalDateTime.now(),
                    temperature = Temperature(24, 33, "°C"),
                    relativeHumidity = "60% - 95%",
                    wind = "NNE 10-15 km/h",
                    details = "Partly cloudy",
                    validPeriod = "Valid for 24 hours from 12:00 PM today",
                    periodRegionForecasts = listOf(
                        PeriodRegionForecastUi(
                            timePeriod = "12:00 PM - 06:00 PM",
                            regions = PeriodRegionForecastUi.RegionsUi(
                                west = WeatherText.THUNDERY_SHOWERS,
                                east = WeatherText.PARTLY_CLOUDY,
                                central = WeatherText.SHOWERS,
                                south = WeatherText.PARTLY_CLOUDY,
                                north = WeatherText.PARTLY_CLOUDY
                            )
                        ),
                        PeriodRegionForecastUi(
                            timePeriod = "06:00 PM - 12:00 AM",
                            regions = PeriodRegionForecastUi.RegionsUi(
                                west = WeatherText.PARTLY_CLOUDY,
                                east = WeatherText.PARTLY_CLOUDY,
                                central = WeatherText.PARTLY_CLOUDY,
                                south = WeatherText.PARTLY_CLOUDY,
                                north = WeatherText.PARTLY_CLOUDY
                            )
                        )
                    )
                )
            ),
            onNavigateToTwoHourForecast = {},
            onNavigateToFourDayForecast = {},
            onWeatherRegionClicked = {}
        )
    }
}