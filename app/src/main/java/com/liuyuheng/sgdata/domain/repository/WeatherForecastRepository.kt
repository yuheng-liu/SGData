package com.liuyuheng.sgdata.domain.repository

import com.liuyuheng.sgdata.data.model.Forecast

interface WeatherForecastRepository {

    suspend fun getWeatherForecast(): List<Forecast>
}