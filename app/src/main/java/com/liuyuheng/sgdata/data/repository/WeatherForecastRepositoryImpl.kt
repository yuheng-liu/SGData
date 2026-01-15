package com.liuyuheng.sgdata.data.repository

import com.liuyuheng.sgdata.data.model.mappers.toDomain
import com.liuyuheng.sgdata.data.network.WeatherForecastApi
import com.liuyuheng.sgdata.data.network.safeApiCall
import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.domain.model.FourDayForecast
import com.liuyuheng.sgdata.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import java.time.LocalDate
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherForecastApi: WeatherForecastApi,
) : WeatherForecastRepository {

    override suspend fun getFourDayForecast(date: LocalDate?): ApiResult<FourDayForecast> =
        safeApiCall {
            val fourDayForecastResponse = weatherForecastApi.getFourDayForecast(date?.toString())
            fourDayForecastResponse.data?.toDomain() ?: FourDayForecast()
        }

    override suspend fun getTwentyFourHourForecast(date: LocalDate?): ApiResult<TwentyFourHourForecast> =
        safeApiCall {
            val twentyFourHourForecastResponse = weatherForecastApi.getTwentyFourHourForecast(date?.toString())
            twentyFourHourForecastResponse.data?.toDomain() ?: TwentyFourHourForecast()
        }
}

