package com.liuyuheng.sgdata.domain.repository

import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.domain.model.FourDayForecast
import com.liuyuheng.sgdata.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.domain.model.TwoHourForecast
import java.time.LocalDate
import java.time.LocalDateTime

interface WeatherForecastRepository {

    suspend fun getFourDayForecast(date: LocalDate?): ApiResult<FourDayForecast>

    suspend fun getTwentyFourHourForecast(date: LocalDate?): ApiResult<TwentyFourHourForecast>

    suspend fun getTwoHourForecast(dateTime: LocalDateTime?): ApiResult<TwoHourForecast>
}