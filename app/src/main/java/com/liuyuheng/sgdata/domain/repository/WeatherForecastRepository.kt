package com.liuyuheng.sgdata.domain.repository

import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.domain.model.weather.FourDayForecast
import com.liuyuheng.sgdata.domain.model.weather.TwentyFourHourForecast
import com.liuyuheng.sgdata.domain.model.weather.TwoHourForecast
import java.time.LocalDate
import java.time.LocalDateTime

interface WeatherForecastRepository {

    suspend fun getFourDayForecast(date: LocalDate?): ApiResult<FourDayForecast>

    suspend fun getTwentyFourHourForecast(date: LocalDate?): ApiResult<TwentyFourHourForecast>

    suspend fun getTwoHourForecast(dateTime: LocalDateTime?): ApiResult<TwoHourForecast>
}