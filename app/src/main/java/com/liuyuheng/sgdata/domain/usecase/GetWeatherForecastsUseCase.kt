package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class GetWeatherForecastsUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {

    suspend fun invoke(): WeatherForecast {
        return weatherForecastRepository.getWeatherForecast()
    }
}