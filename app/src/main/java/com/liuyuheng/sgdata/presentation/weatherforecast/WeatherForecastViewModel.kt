package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.domain.usecase.GetFourDayForecastUseCase
import com.liuyuheng.sgdata.domain.usecase.GetTwentyFourHourForecastUseCase
import com.liuyuheng.sgdata.domain.usecase.GetTwoHourForecastUseCase
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.loader.LoadingStateHandler
import com.liuyuheng.sgdata.presentation.shared.loader.withLoading
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.FourDayForecastUi
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.FourDayForecastUiState
import com.liuyuheng.sgdata.presentation.weatherforecast.fourday.toUi
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.TwentyFourHourForecastUiState
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.toUi
import com.liuyuheng.sgdata.presentation.weatherforecast.twohour.TwoHourForecastUiState
import com.liuyuheng.sgdata.utils.toLocalDateOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val loadingStateHandler: LoadingStateHandler,
    private val getFourDayForecastUseCase: GetFourDayForecastUseCase,
    private val getTwentyFourHourForecastUseCase: GetTwentyFourHourForecastUseCase,
    private val getTwoHourForecastUseCase: GetTwoHourForecastUseCase
) : ViewModel() {

    // 4 days
    private val _fourDayForecastUiState = MutableStateFlow(FourDayForecastUiState())
    val fourDayForecastUiState = _fourDayForecastUiState.asStateFlow()

    // 24 Hours
    private val _twentyFourHourForecastUiState = MutableStateFlow(TwentyFourHourForecastUiState())
    val twentyFourHourForecastUiState = _twentyFourHourForecastUiState.asStateFlow()

    // 2 Hours
    private val _twoHoursForecastUiState = MutableStateFlow(TwoHourForecastUiState())
    val twoHoursForecastUiState = _twoHoursForecastUiState.asStateFlow()

    // error dialog
    private val _dialogState = MutableStateFlow<DialogTypes?>(null)
    val dialogState = _dialogState.asStateFlow()

    val todayMillis: Long = LocalDate.now()
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    init {
        observeSelectedForecastDate()
    }

    private fun observeSelectedForecastDate() {
        _twentyFourHourForecastUiState.map { it.selectedDate }
            .distinctUntilChanged()
            .filterNotNull()
            .onEach {
                fetchTwentyFourHoursForecast()
            }
            .launchIn(viewModelScope)
    }

    fun setSelectedDate(date: LocalDate?) {
        _fourDayForecastUiState.update {
            it.copy(
                selectedDate = date
            )
        }
    }

    fun fetchFourDayForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val fourDayForecast = getFourDayForecastUseCase.invoke(_fourDayForecastUiState.value.selectedDate)) {
                is ApiResult.Success -> {
                    _fourDayForecastUiState.update {
                        it.copy(
                            fourDayForecast = fourDayForecast.data.toUi()
                        )
                    }
                }

                is ApiResult.Error -> {
                    _dialogState.value = DialogTypes.HttpError(fourDayForecast.message ?: "")
                }
            }
        }
    }

    fun fetchTwentyFourHoursForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val twentyFourHourForecast = getTwentyFourHourForecastUseCase.invoke(_twentyFourHourForecastUiState.value.selectedDate)) {
                is ApiResult.Success -> {
                    _twentyFourHourForecastUiState.update {
                        it.copy(
                            twentyFourHourForecast = twentyFourHourForecast.data.toUi()
                        )
                    }
                }

                is ApiResult.Error -> {
                    _dialogState.value = DialogTypes.HttpError(twentyFourHourForecast.message ?: "")
                }
            }
        }
    }

    fun onForecastCardSelected(data: FourDayForecastUi.DayForecast) {
        _twentyFourHourForecastUiState.update {
            it.copy(
                selectedDate = data.date.toLocalDateOrNull()
            )
        }
    }

    fun fetchTwoHourForecast() = viewModelScope.launch {
        val selectedDateTime = _twentyFourHourForecastUiState.value.selectedDate?.atTime(twoHoursForecastUiState.value.selectedTimeslot)

        loadingStateHandler.withLoading {
            when (val twoHourForecast = getTwoHourForecastUseCase.invoke(selectedDateTime)) {
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

    fun onTwoHourTimeslotSelected(timeslot: LocalTime) {
        _twoHoursForecastUiState.update {
            it.copy(
                selectedTimeslot = timeslot
            )
        }
    }

    fun onDismissErrorDialog() {
        _dialogState.value = null
    }
}