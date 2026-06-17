package com.liuyuheng.sgdata.weatherforecast.presentation.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.R
import com.liuyuheng.sgdata.core.presentation.components.BasePreviewComposable
import com.liuyuheng.sgdata.core.presentation.components.SGDataSpacer
import com.liuyuheng.sgdata.core.presentation.theme.Dimensions
import com.liuyuheng.sgdata.core.utils.toDisplayDateV2
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.RelativeHumidity
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Temperature
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.WeatherText
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.Wind
import java.time.LocalDateTime

@Composable
fun GeneralWeatherCard(
    dateTime: LocalDateTime?,
    temperature: Temperature,
    relativeHumidity: RelativeHumidity,
    wind: Wind,
    weatherText: WeatherText,
) {
    Card {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(all = Dimensions.paddingLarge),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(dateTime?.toDisplayDateV2() ?: "")
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
                    ) {
                        Row {
                            Image(
                                modifier = Modifier.size(Dimensions.iconSizeMedium),
                                imageVector = ImageVector.vectorResource(R.drawable.ic_highest_temp),
                                contentDescription = null
                            )
                            Text("${temperature.high}${temperature.unit}")
                        }
                        Row {
                            Image(
                                modifier = Modifier.size(Dimensions.iconSizeMedium),
                                imageVector = ImageVector.vectorResource(R.drawable.ic_lowest_temp),
                                contentDescription = null
                            )
                            Text("${temperature.low}${temperature.unit}")
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
                    ) {
                        Row {
                            Image(
                                modifier = Modifier.size(Dimensions.iconSizeMedium),
                                imageVector = ImageVector.vectorResource(R.drawable.ic_water_droplet),
                                contentDescription = null
                            )
                            Text(getRelativeHumidityString(relativeHumidity))
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(Dimensions.iconSizeMedium),
                                imageVector = ImageVector.vectorResource(R.drawable.image_wind),
                                contentDescription = null
                            )
                            Text(getWindString(wind))
                        }
                    }
                }
            }
            SGDataSpacer(Dimensions.paddingLarge)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(Dimensions.imageSizeSmall),
                    imageVector = ImageVector.vectorResource(
                        WeatherText.getImageResource(weatherText)
                    ),
                    contentDescription = null
                )
                Text(weatherText.displayString)
            }
        }
    }
}

@Preview
@Composable
private fun GeneralWeatherCard_Preview() {
    BasePreviewComposable {
        GeneralWeatherCard(
            dateTime = LocalDateTime.now(),
            temperature = Temperature(
                low = 25,
                high = 33,
                unit = UNIT_DEGREE_CELSIUS
            ),
            relativeHumidity = RelativeHumidity(
                low = 60,
                high = 95,
                unit = "%"
            ),
            wind = Wind(
                speed = Wind.WindSpeed(
                    low = 10,
                    high = 20
                ),
                direction = "SW"
            ),
            weatherText = WeatherText.PARTLY_CLOUDY
        )
    }
}