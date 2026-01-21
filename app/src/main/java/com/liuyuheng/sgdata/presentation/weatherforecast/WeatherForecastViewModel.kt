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
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.TwentyFourHourForecastUiState
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.toUi
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
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val loadingStateHandler: LoadingStateHandler,
    private val getFourDayForecastUseCase: GetFourDayForecastUseCase,
    private val getTwentyFourHourForecastUseCase: GetTwentyFourHourForecastUseCase
) : ViewModel() {

    private val fourDayForecastUiState = MutableStateFlow(FourDayForecastUiState())
    val fourDayForecastState = fourDayForecastUiState.asStateFlow()

    private val twentyFourHoursForecastUiState = MutableStateFlow(TwentyFourHourForecastUiState())
    val twentyFourHoursUiState = twentyFourHoursForecastUiState.asStateFlow()

    val todayMillis: Long = LocalDate.now()
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    init {
        observeSelectedForecastDate()
    }

    private fun observeSelectedForecastDate() {
        twentyFourHoursForecastUiState.map { it.selectedDate }
            .distinctUntilChanged()
            .filterNotNull()
            .onEach {
                fetchTwentyFourHoursForecast()
            }
            .launchIn(viewModelScope)
    }

    fun setSelectedDate(date: LocalDate?) {
        fourDayForecastUiState.update {
            it.copy(
                selectedDate = date
            )
        }
    }

    fun fetchFourDayForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val fourDayForecast = getFourDayForecastUseCase.invoke(fourDayForecastUiState.value.selectedDate)) {
                is ApiResult.Success -> {
                    fourDayForecastUiState.update {
                        it.copy(
                            fourDayForecast = fourDayForecast.data.toUi()
                        )
                    }
                }

                is ApiResult.Error -> {
                    fourDayForecastUiState.update {
                        it.copy(
                            currentDialog = DialogTypes.HttpError(fourDayForecast.message ?: "")
                        )
                    }
                }
            }
        }
    }

    fun fetchTwentyFourHoursForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val twentyFourHourForecast = getTwentyFourHourForecastUseCase.invoke(twentyFourHoursForecastUiState.value.selectedDate)) {
                is ApiResult.Success -> {
                    twentyFourHoursForecastUiState.update {
                        it.copy(
                            twentyFourHourForecast = twentyFourHourForecast.data.toUi()
                        )
                    }
                }

                is ApiResult.Error -> {
                    twentyFourHoursForecastUiState.update {
                        it.copy(
                            currentDialog = DialogTypes.HttpError(twentyFourHourForecast.message ?: "")
                        )
                    }
                }
            }
        }
    }

    fun onForecastCardSelected(data: FourDayForecastUi.DayForecast) {
        twentyFourHoursForecastUiState.update {
            it.copy(
                selectedDate = data.date.toLocalDateOrNull()
            )
        }
    }

    fun onDismissErrorDialog(forecastType: WeatherForecastType) {
        when (forecastType) {
            WeatherForecastType.FOUR_DAY -> fourDayForecastUiState.update {
                it.copy(
                    currentDialog = null
                )
            }

            WeatherForecastType.TWENTY_FOUR_HOURS -> twentyFourHoursForecastUiState.update {
                it.copy(
                    currentDialog = null
                )
            }
        }
    }

    enum class WeatherForecastType {
        FOUR_DAY,
        TWENTY_FOUR_HOURS
    }
}