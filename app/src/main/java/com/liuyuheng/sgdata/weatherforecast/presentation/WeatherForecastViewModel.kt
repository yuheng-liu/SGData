package com.liuyuheng.sgdata.weatherforecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.core.domain.models.DomainResult
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
                is DomainResult.Success -> {
                    _twentyFourHoursForecastUiState.value = TwentyFourHourForecastUiState.Loaded(
                        twentyFourHourForecast = twentyFourHourForecast.data.toUi()
                    )
                }

                is DomainResult.Failure.NoData -> {
                    _twentyFourHoursForecastUiState.value = TwentyFourHourForecastUiState.Error("No data available")
                    _dialogState.value = DialogTypes.SimpleError(twentyFourHourForecast.message)
                }

                is DomainResult.Failure.Unavailable -> {
                    _twentyFourHoursForecastUiState.value = TwentyFourHourForecastUiState.Error("Unavailable")
                    _dialogState.value = DialogTypes.SimpleError(twentyFourHourForecast.message)
                }
            }
        }
    }

    fun fetchTwoHoursForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val twoHourForecast = getTwoHourForecastUseCase()) {
                is DomainResult.Success -> {
                    _twoHoursForecastUiState.update {
                        it.copy(
                            twoHourForecast = twoHourForecast.data
                        )
                    }
                }

                is DomainResult.Failure.NoData -> {
                    _dialogState.value = DialogTypes.SimpleError(twoHourForecast.message)
                }

                is DomainResult.Failure.Unavailable -> {
                    _dialogState.value = DialogTypes.SimpleError(twoHourForecast.message)
                }
            }
        }
    }

    fun fetchFourDaysForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val fourDayForecast = getFourDayForecastUseCase(_fourDaysForecastUiState.value.selectedDate)) {
                is DomainResult.Success -> {
                    _fourDaysForecastUiState.update {
                        it.copy(
                            fourDayForecast = fourDayForecast.data
                        )
                    }
                }

                is DomainResult.Failure.NoData -> {
                    _dialogState.value = DialogTypes.SimpleError(fourDayForecast.message)
                }

                is DomainResult.Failure.Unavailable -> {
                    _dialogState.value = DialogTypes.SimpleError(fourDayForecast.message)
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