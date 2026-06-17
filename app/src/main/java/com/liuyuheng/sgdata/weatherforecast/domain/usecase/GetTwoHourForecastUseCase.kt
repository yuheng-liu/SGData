package com.liuyuheng.sgdata.weatherforecast.domain.usecase

import com.liuyuheng.sgdata.weatherforecast.domain.repository.WeatherForecastRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetTwoHourForecastUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {
    suspend operator fun invoke(date: LocalDateTime? = null) = weatherForecastRepository.getTwoHourForecast(date)
}