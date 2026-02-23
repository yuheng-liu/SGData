package com.liuyuheng.sgdata.presentation.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.domain.usecase.GetTwentyFourHourForecastUseCase
import com.liuyuheng.sgdata.presentation.shared.dialog.DialogTypes
import com.liuyuheng.sgdata.presentation.shared.loader.LoadingStateHandler
import com.liuyuheng.sgdata.presentation.shared.loader.withLoading
import com.liuyuheng.sgdata.presentation.weatherforecast.shared.WeatherRegion
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.TwentyFourHourForecastUiState
import com.liuyuheng.sgdata.presentation.weatherforecast.twentyfourhour.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeatherForecastV2ViewModel @Inject constructor(
    private val loadingStateHandler: LoadingStateHandler,
    private val getTwentyFourHourForecastUseCase: GetTwentyFourHourForecastUseCase,
) : ViewModel() {

    // 24 Hours
    private val _twentyFourHourForecastUiState = MutableStateFlow(TwentyFourHourForecastUiState())
    val twentyFourHourForecastUiState = _twentyFourHourForecastUiState.asStateFlow()

    // error dialog
    private val _dialogState = MutableStateFlow<DialogTypes?>(null)
    val dialogState = _dialogState.asStateFlow()

    init {
        fetchTwentyFourHoursForecast()
    }

    fun fetchTwentyFourHoursForecast() = viewModelScope.launch {
        loadingStateHandler.withLoading {
            when (val twentyFourHourForecast = getTwentyFourHourForecastUseCase(LocalDate.now())) {
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

    fun onWeatherRegionClicked(weatherRegion: WeatherRegion) {

    }

    fun onDismissErrorDialog() {
        _dialogState.value = null
    }
}