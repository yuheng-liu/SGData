package com.liuyuheng.sgdata.weatherforecast.presentation.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.core.presentation.components.BasePreviewComposable
import com.liuyuheng.sgdata.core.presentation.theme.Dimensions
import com.liuyuheng.sgdata.weatherforecast.domain.model.weather.WeatherText

@Composable
fun WeatherTextCard(
    text: String,
    weatherText: WeatherText,
) {
    Card(
        modifier = Modifier.height(Dimensions.cardRowHeight),
        onClick = {},
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Dimensions.paddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.5f),
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
            Column(
                modifier = Modifier.weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
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
private fun WeatherTextCardPreview() {
    BasePreviewComposable {
        WeatherTextCard("Central", WeatherText.PARTLY_CLOUDY)
    }
}