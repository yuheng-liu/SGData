package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import com.liuyuheng.sgdata.presentation.shared.loader.LoadingStateHandler
import com.liuyuheng.sgdata.presentation.shared.loader.withLoading
import java.time.LocalDate
import javax.inject.Inject

class GetWeatherForecastsUseCase @Inject constructor(
    private val loadingStateHandler: LoadingStateHandler,
    private val weatherForecastRepository: WeatherForecastRepository,
) {

    suspend fun invoke(date: LocalDate?): WeatherForecast {
        return loadingStateHandler.withLoading {
            weatherForecastRepository.getWeatherForecast(date)
        }
    }
}