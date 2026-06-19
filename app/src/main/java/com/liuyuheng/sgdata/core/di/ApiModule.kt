package com.liuyuheng.sgdata.core.di

import com.liuyuheng.sgdata.carparkavailability.data.api.CarparkAvailabilityApi
import com.liuyuheng.sgdata.core.data.network.api.DatasetDownloadApi
import com.liuyuheng.sgdata.weatherforecast.data.api.WeatherForecastApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideWeatherForecastApi(
        @RealTimeEndpoint retrofit: Retrofit
    ): WeatherForecastApi = retrofit.create(WeatherForecastApi::class.java)

    @Provides
    @Singleton
    fun provideDataSetDownloadApi(
        @DatasetDownloadEndpoint retrofit: Retrofit,
    ): DatasetDownloadApi = retrofit.create(DatasetDownloadApi::class.java)

    @Provides
    @Singleton
    fun provideCarparkAvailabilityApi(
        @DataV1ApiEndpoint retrofit: Retrofit,
    ): CarparkAvailabilityApi = retrofit.create(CarparkAvailabilityApi::class.java)
}