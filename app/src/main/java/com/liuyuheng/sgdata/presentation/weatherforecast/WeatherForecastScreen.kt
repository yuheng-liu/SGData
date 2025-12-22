package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable

@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val weatherForecast by viewModel.weatherForecast.collectAsStateWithLifecycle()

    WeatherForecastScreen(
        weatherForecast = weatherForecast,
        modifier = modifier,
        onButtonClicked = viewModel::loadWeatherForecasts,
    )
}

@Composable
fun WeatherForecastScreen(
    weatherForecast: WeatherForecast,
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit,
) {
    weatherForecast.forecastsMap.forEach { (date, forecasts) ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(forecasts.size) { index ->
                val forecast = forecasts[index]
                Text(text = "forecast: ${forecast.temperature}")
            }
        }
    }
    Button(
        onClick = { onButtonClicked() }
    ) {
        Text(text = "Refresh")
    }
}

@Preview
@Composable
fun WeatherForecastScreenPreview() {
    BasePreviewComposable {
        WeatherForecastScreen(
            weatherForecast = WeatherForecast(),
            onButtonClicked = {},
        )
    }
}