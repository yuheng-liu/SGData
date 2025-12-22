package com.liuyuheng.sgdata.di

import com.liuyuheng.sgdata.data.repository.WeatherForecastRepositoryImpl
import com.liuyuheng.sgdata.domain.repository.WeatherForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindWeatherForecastRepository(
        impl: WeatherForecastRepositoryImpl,
    ): WeatherForecastRepository
}
