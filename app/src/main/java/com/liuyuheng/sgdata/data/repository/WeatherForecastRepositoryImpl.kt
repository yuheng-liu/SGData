package com.liuyuheng.sgdata.data.repository


import com.liuyuheng.sgdata.data.model.toDomain
import com.liuyuheng.sgdata.data.network.WeatherForecastApi
import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import java.time.LocalDate
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherForecastApi: WeatherForecastApi,
) : WeatherForecastRepository {

    override suspend fun getWeatherForecast(date: LocalDate?): WeatherForecast {
        val response = weatherForecastApi.getWeatherForecast(date?.toString())
        return response.data.toDomain()
    }
}

