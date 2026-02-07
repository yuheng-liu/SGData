package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import java.time.LocalDate
import javax.inject.Inject

class GetFourDayForecastUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {

    suspend operator fun invoke(date: LocalDate?) = weatherForecastRepository.getFourDayForecast(date)
}