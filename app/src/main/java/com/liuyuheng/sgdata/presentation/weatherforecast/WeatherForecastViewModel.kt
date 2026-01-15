package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.domain.usecase.GetFourDayForecastUseCase
import com.liuyuheng.sgdata.domain.usecase.GetTwentyFourHourForecastUseCase
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.loader.LoadingStateHandler
import com.liuyuheng.sgdata.presentation.shared.loader.withLoading
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.FourDayForecastUi
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.FourDayForecastUiState
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val loadingStateHandler: LoadingStateHandler,
    private val getFourDayForecastUseCase: GetFourDayForecastUseCase,
    private val getTwentyFourHourForecastUseCase: GetTwentyFourHourForecastUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FourDayForecastUiState())
    val uiState = _uiState.asStateFlow()

    val todayMillis: Long = LocalDate.now()
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    fun setSelectedDate(date: LocalDate?) {
        _uiState.update {
            it.copy(
                selectedDate = date
            )
        }
    }

    fun loadWeatherForecasts() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val fourDayForecast = getFourDayForecastUseCase.invoke(_uiState.value.selectedDate)) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            fourDayForecast = fourDayForecast.data.toUi()
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            currentDialog = DialogTypes.HttpError(fourDayForecast.message ?: "")
                        )
                    }
                }
            }
        }
    }

    fun onForecastCardSelected(data: FourDayForecastUi.ForecastUi) {
        _uiState.update {
            it.copy(
                selectedForecast = data
            )
        }
    }

    fun onDismissDialog() {
        _uiState.update {
            it.copy(
                currentDialog = null
            )
        }
    }
}