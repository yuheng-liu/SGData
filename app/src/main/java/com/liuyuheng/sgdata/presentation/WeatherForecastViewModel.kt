package com.liuyuheng.sgdata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.data.model.Forecast
import com.liuyuheng.sgdata.domain.usecase.GetWeatherForecastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
) : ViewModel() {

    private val _weatherForecasts = MutableStateFlow<List<Forecast>>(emptyList())
    val weatherForecasts: StateFlow<List<Forecast>> = _weatherForecasts

    fun loadWeatherForecasts() = viewModelScope.launch {
        _weatherForecasts.value = getWeatherForecastsUseCase.invoke()
    }
}