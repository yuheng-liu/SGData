package com.liuyuheng.sgdata.weatherforecast.data.repository

import com.liuyuheng.sgdata.core.data.models.ApiResult
import com.liuyuheng.sgdata.core.data.network.safeApiCall
import com.liuyuheng.sgdata.core.domain.models.DomainResult
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

    override suspend fun getFourDayForecast(date: LocalDate?): DomainResult<FourDayForecast> {
        val apiResult = safeApiCall {
            val fourDayForecastResponse = weatherForecastApi.getFourDayForecast(date?.toString())
            fourDayForecastResponse.data?.toDomain() ?: FourDayForecast()
        }
        return when (apiResult) {
            is ApiResult.Success -> {
                DomainResult.Success(apiResult.data)
            }

            is ApiResult.Error.HttpError -> DomainResult.Failure.NoData(apiResult.message)
            is ApiResult.Error.NetworkError -> DomainResult.Failure.NoData(apiResult.message)
            is ApiResult.Error.UnknownError -> DomainResult.Failure.Unavailable(apiResult.message)
        }
    }

    override suspend fun getTwentyFourHourForecast(date: LocalDate?): DomainResult<TwentyFourHourForecast> {
        val apiResult = safeApiCall {
            val twentyFourHourForecastResponse = weatherForecastApi.getTwentyFourHourForecast(date?.toString())
            twentyFourHourForecastResponse.data?.toDomain() ?: TwentyFourHourForecast()
        }
        return when (apiResult) {
            is ApiResult.Success -> {
                DomainResult.Success(apiResult.data)
            }

            is ApiResult.Error.HttpError -> DomainResult.Failure.NoData(apiResult.message)
            is ApiResult.Error.NetworkError -> DomainResult.Failure.NoData(apiResult.message)
            is ApiResult.Error.UnknownError -> DomainResult.Failure.Unavailable(apiResult.message)
        }
    }

    override suspend fun getTwoHourForecast(dateTime: LocalDateTime?): DomainResult<TwoHourForecast> {
        val formattedDateTime = dateTime?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        val apiResult = safeApiCall {
            val twoHourForecastResponse = weatherForecastApi.getTwoHourForecast(formattedDateTime)
            twoHourForecastResponse.data?.toDomain() ?: TwoHourForecast()
        }
        return when (apiResult) {
            is ApiResult.Success -> {
                DomainResult.Success(apiResult.data)
            }

            is ApiResult.Error.HttpError -> DomainResult.Failure.NoData(apiResult.message)
            is ApiResult.Error.NetworkError -> DomainResult.Failure.NoData(apiResult.message)
            is ApiResult.Error.UnknownError -> DomainResult.Failure.Unavailable(apiResult.message)
        }
    }
}