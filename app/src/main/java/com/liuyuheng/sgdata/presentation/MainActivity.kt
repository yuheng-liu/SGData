package com.liuyuheng.sgdata.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.data.model.Forecast
import com.liuyuheng.sgdata.presentation.theme.SGDataTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadWeatherForecasts()
        setContent {
            SGDataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val weatherForecasts by viewModel.weatherForecasts.collectAsStateWithLifecycle()

                    ForecastsList(
                        forecasts = weatherForecasts,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun ForecastsList(
    forecasts: List<Forecast>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(forecasts) { forecast ->
            Text(text = "forecast: ${forecast.temperature}")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ForecastsListPreview() {
//    SGDataTheme {
//        ForecastsList(forecasts = listOf(
//            Forecast(
//
//            )
//        ))
//    }
//}
