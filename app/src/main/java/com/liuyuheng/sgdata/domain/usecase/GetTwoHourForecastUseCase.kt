package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetTwoHourForecastUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {
    suspend fun invoke(date: LocalDateTime?) = weatherForecastRepository.getTwoHourForecast(date)
}