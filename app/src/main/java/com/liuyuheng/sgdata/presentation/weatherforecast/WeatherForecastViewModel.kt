package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.usecase.GetWeatherForecastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherForecastUiState())
    val uiState = _uiState.asStateFlow()

    fun setSelectedDate(date: LocalDate?) {
        _uiState.update {
            it.copy(
                selectedDate = date
            )
        }
    }

    fun loadWeatherForecasts() = viewModelScope.launch {
        val weatherForecast = getWeatherForecastsUseCase.invoke(_uiState.value.selectedDate)

        _uiState.update {
            it.copy(
                weatherForecast = weatherForecast
            )
        }
    }
}