package com.liuyuheng.sgdata.data.repository

import com.liuyuheng.sgdata.data.model.Forecast
import com.liuyuheng.sgdata.data.network.WeatherForecastApi
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherForecastApi: WeatherForecastApi,
) : WeatherForecastRepository {

    override suspend fun getWeatherForecast(): List<Forecast> {
        val response = weatherForecastApi.getWeatherForecast()
        return response.data.records.flatMap { it.forecasts }
    }
}