package com.liuyuheng.sgdata.di

import com.liuyuheng.sgdata.data.network.WeatherForecastApi
import com.liuyuheng.sgdata.data.repository.WeatherForecastRepositoryImpl
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(
        weatherForecastApi: WeatherForecastApi,
    ): WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(weatherForecastApi)
    }
}
