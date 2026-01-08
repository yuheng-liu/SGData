package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import java.time.LocalDate
import javax.inject.Inject

class GetWeatherForecastsUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {

    suspend fun invoke(date: LocalDate?): WeatherForecast {
        return weatherForecastRepository.getWeatherForecast(date)
    }
}