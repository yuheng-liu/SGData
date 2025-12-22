package com.liuyuheng.sgdata.domain.repository

import com.liuyuheng.sgdata.domain.model.WeatherForecast

interface WeatherForecastRepository {

    suspend fun getWeatherForecast(): WeatherForecast
}