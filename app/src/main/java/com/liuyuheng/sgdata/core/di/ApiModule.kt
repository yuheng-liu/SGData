package com.liuyuheng.sgdata.core.di

import com.liuyuheng.sgdata.datasetdownload.data.DatasetDownloadApi
import com.liuyuheng.sgdata.weatherforecast.data.WeatherForecastApi
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
    fun provideDataStoreSearchApi(
        @DatasetEndpoint retrofit: Retrofit,
    ): DatasetDownloadApi = retrofit.create(DatasetDownloadApi::class.java)
}