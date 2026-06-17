package com.liuyuheng.sgdata.core.di

import com.liuyuheng.sgdata.carparkavailability.data.repositories.CarparkInfoRepositoryImpl
import com.liuyuheng.sgdata.carparkavailability.domain.repositories.CarparkInfoRepository
import com.liuyuheng.sgdata.core.data.repositories.MetadataRepositoryImpl
import com.liuyuheng.sgdata.core.domain.repositories.MetadataRepository
import com.liuyuheng.sgdata.weatherforecast.data.repository.WeatherForecastRepositoryImpl
import com.liuyuheng.sgdata.weatherforecast.domain.repository.WeatherForecastRepository
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

    @Singleton
    @Binds
    abstract fun bindCarparkInfoRepository(
        impl: CarparkInfoRepositoryImpl,
    ): CarparkInfoRepository

    @Singleton
    @Binds
    abstract fun bindMetadataRepository(
        impl: MetadataRepositoryImpl,
    ): MetadataRepository
}