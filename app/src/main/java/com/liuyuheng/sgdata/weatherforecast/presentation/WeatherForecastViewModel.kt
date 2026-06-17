package com.liuyuheng.sgdata.weatherforecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.core.domain.models.ApiResult
import com.liuyuheng.sgdata.core.presentation.components.dialog.DialogTypes
import com.liuyuheng.sgdata.core.presentation.components.loader.LoadingStateHandler
import com.liuyuheng.sgdata.core.presentation.components.loader.withLoading
import com.liuyuheng.sgdata.weatherforecast.domain.usecase.GetFourDayForecastUseCase
import com.liuyuheng.sgdata.weatherforecast.domain.usecase.GetTwentyFourHourForecastUseCase
import com.liuyuheng.sgdata.weatherforecast.domain.usecase.GetTwoHourForecastUseCase
import com.liuyuheng.sgdata.weatherforecast.presentation.fourday.FourDayForecastUiState
import com.liuyuheng.sgdata.weatherforecast.presentation.twentyfourhour.TwentyFourHourForecastUiState
import com.liuyuheng.sgdata.weatherforecast.presentation.twentyfourhour.toUi
import com.liuyuheng.sgdata.weatherforecast.presentation.twohour.TwoHourForecastUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val loadingStateHandler: LoadingStateHandler,
    private val getTwentyFourHourForecastUseCase: GetTwentyFourHourForecastUseCase,
    private val getTwoHourForecastUseCase: GetTwoHourForecastUseCase,
    private val getFourDayForecastUseCase: GetFourDayForecastUseCase,
) : ViewModel() {

    // 24 Hours
    private val _twentyFourHoursForecastUiState = MutableStateFlow<TwentyFourHourForecastUiState>(TwentyFourHourForecastUiState.Idle)
    val twentyFourHoursForecastUiState = _twentyFourHoursForecastUiState.asStateFlow()

    // 2 Hours
    private val _twoHoursForecastUiState = MutableStateFlow(TwoHourForecastUiState())
    val twoHoursForecastUiState = _twoHoursForecastUiState.asStateFlow()

    // 4 Days
    private val _fourDaysForecastUiState = MutableStateFlow(FourDayForecastUiState())
    val fourDaysForecastUiState = _fourDaysForecastUiState.asStateFlow()

    // error dialog
    private val _dialogState = MutableStateFlow<DialogTypes?>(null)
    val dialogState = _dialogState.asStateFlow()

    init {
        fetchTwentyFourHoursForecast()
    }

    fun fetchTwentyFourHoursForecast() = viewModelScope.launch {
        _twentyFourHoursForecastUiState.value = TwentyFourHourForecastUiState.Loading
        loadingStateHandler.withLoading {
            when (val twentyFourHourForecast = getTwentyFourHourForecastUseCase(LocalDate.now())) {
                is ApiResult.Success -> {
                    _twentyFourHoursForecastUiState.value = TwentyFourHourForecastUiState.Loaded(
                        twentyFourHourForecast = twentyFourHourForecast.data.toUi()
                    )
                }

                is ApiResult.Error -> {
                    _twentyFourHoursForecastUiState.value = TwentyFourHourForecastUiState.Error("No data available")
                    _dialogState.value = DialogTypes.HttpError(twentyFourHourForecast.message ?: "")
                }
            }
        }
    }

    fun fetchTwoHoursForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val twoHourForecast = getTwoHourForecastUseCase()) {
                is ApiResult.Success -> {
                    _twoHoursForecastUiState.update {
                        it.copy(
                            twoHourForecast = twoHourForecast.data
                        )
                    }
                }

                is ApiResult.Error -> {
                    _dialogState.value = DialogTypes.HttpError(twoHourForecast.message ?: "")
                }
            }
        }
    }

    fun fetchFourDaysForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val fourDayForecast = getFourDayForecastUseCase(_fourDaysForecastUiState.value.selectedDate)) {
                is ApiResult.Success -> {
                    _fourDaysForecastUiState.update {
                        it.copy(
                            fourDayForecast = fourDayForecast.data
                        )
                    }
                }

                is ApiResult.Error -> {
                    _dialogState.value = DialogTypes.HttpError(fourDayForecast.message ?: "")
                }
            }
        }
    }

    fun setSelectedDate(date: LocalDate?) {
        date?.let {
            _fourDaysForecastUiState.update {
                it.copy(
                    selectedDate = date
                )
            }
            fetchFourDaysForecast()
        }
    }

    fun onDismissErrorDialog() {
        _dialogState.value = null
    }
}