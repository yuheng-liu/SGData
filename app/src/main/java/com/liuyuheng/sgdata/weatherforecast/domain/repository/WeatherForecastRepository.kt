package com.liuyuheng.sgdata.weatherforecast.domain.repository

import com.liuyuheng.sgdata.core.domain.models.DomainResult
import com.liuyuheng.sgdata.weatherforecast.domain.model.FourDayForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwoHourForecast
import java.time.LocalDate
import java.time.LocalDateTime

interface WeatherForecastRepository {

    suspend fun getFourDayForecast(date: LocalDate?): DomainResult<FourDayForecast>

    suspend fun getTwentyFourHourForecast(date: LocalDate?): DomainResult<TwentyFourHourForecast>

    suspend fun getTwoHourForecast(dateTime: LocalDateTime?): DomainResult<TwoHourForecast>
}