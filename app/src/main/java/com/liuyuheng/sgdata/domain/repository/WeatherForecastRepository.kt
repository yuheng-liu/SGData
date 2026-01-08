package com.liuyuheng.sgdata.domain.repository

import com.liuyuheng.sgdata.domain.model.WeatherForecast
import java.time.LocalDate

interface WeatherForecastRepository {

    suspend fun getWeatherForecast(date: LocalDate?): WeatherForecast
}