package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.domain.usecase.GetWeatherForecastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
) : ViewModel() {

    private val _weatherForecast = MutableStateFlow(WeatherForecast())
    val weatherForecast = _weatherForecast
        .onStart { loadWeatherForecasts() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            WeatherForecast(),
        )

    fun loadWeatherForecasts() = viewModelScope.launch {
        _weatherForecast.value = getWeatherForecastsUseCase.invoke()
    }
}