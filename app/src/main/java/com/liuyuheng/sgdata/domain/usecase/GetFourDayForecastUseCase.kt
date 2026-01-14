package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.domain.model.FourDayForecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import java.time.LocalDate
import javax.inject.Inject

class GetFourDayForecastUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {

    suspend fun invoke(date: LocalDate?): ApiResult<FourDayForecast> {
        return weatherForecastRepository.getFourDayForecast(date)
    }
}