package com.liuyuheng.sgdata.data.repository


import com.liuyuheng.sgdata.data.model.toDomain
import com.liuyuheng.sgdata.data.network.WeatherForecastApi
import com.liuyuheng.sgdata.domain.model.WeatherForecast
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherForecastApi: WeatherForecastApi,
) : WeatherForecastRepository {

    override suspend fun getWeatherForecast(date: LocalDate?): WeatherForecast {
        return withContext(Dispatchers.IO) {
            val response = weatherForecastApi.getWeatherForecast(date?.toString())
            response.data.toDomain()
        }
    }
}

