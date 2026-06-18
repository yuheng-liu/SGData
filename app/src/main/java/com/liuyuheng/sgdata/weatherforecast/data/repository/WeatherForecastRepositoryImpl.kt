package com.liuyuheng.sgdata.weatherforecast.data.repository

import com.liuyuheng.sgdata.core.data.network.safeApiCall
import com.liuyuheng.sgdata.core.domain.models.ApiResult
import com.liuyuheng.sgdata.weatherforecast.data.api.WeatherForecastApi
import com.liuyuheng.sgdata.weatherforecast.data.mapper.toDomain
import com.liuyuheng.sgdata.weatherforecast.domain.model.FourDayForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwoHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.repository.WeatherForecastRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    override suspend fun getTwoHourForecast(dateTime: LocalDateTime?): ApiResult<TwoHourForecast> {
        val formattedDateTime = dateTime?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return safeApiCall {
            val twoHourForecastResponse = weatherForecastApi.getTwoHourForecast(formattedDateTime)
            twoHourForecastResponse.data?.toDomain() ?: TwoHourForecast()
        }
    }
}