package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.data.model.Forecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class GetWeatherForecastsUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {

    suspend fun invoke(): List<Forecast> {
        return weatherForecastRepository.getWeatherForecast()
    }
}