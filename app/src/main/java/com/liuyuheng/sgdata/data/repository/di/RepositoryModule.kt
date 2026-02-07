package com.liuyuheng.sgdata.data.repository.di

import com.liuyuheng.sgdata.data.repository.CarparkInfoRepositoryImpl
import com.liuyuheng.sgdata.data.repository.MetadataRepositoryImpl
import com.liuyuheng.sgdata.data.repository.WeatherForecastRepositoryImpl
import com.liuyuheng.sgdata.domain.repository.CarparkInfoRepository
import com.liuyuheng.sgdata.domain.repository.MetadataRepository
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