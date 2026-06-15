package com.liuyuheng.sgdata.presentation.weatherforecast.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.liuyuheng.sgdata.domain.model.weather.shared.RelativeHumidity
import com.liuyuheng.sgdata.domain.model.weather.shared.Temperature
import com.liuyuheng.sgdata.domain.model.weather.shared.WeatherText
import com.liuyuheng.sgdata.domain.model.weather.shared.Wind
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.shared.toDisplayDateV2
import java.time.LocalDateTime

@Composable
fun GeneralWeatherCard(
    dateTime: LocalDateTime?,
    temperature: Temperature,
    relativeHumidity: RelativeHumidity,
    wind: Wind,
    weatherText: WeatherText,
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
            Text(dateTime?.toDisplayDateV2()?.replaceFirst(" ", "\n") ?: "")
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
            Column {
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
            Image(
                modifier = Modifier.size(Dimensions.imageSizeSmall),
                imageVector = ImageVector.vectorResource(
                    WeatherText.getImageResource(weatherText)
                ),
                contentDescription = null
            )
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
                unit = Constants.UNIT_DEGREE_CELSIUS
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