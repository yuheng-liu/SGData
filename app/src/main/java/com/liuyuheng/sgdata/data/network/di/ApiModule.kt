package com.liuyuheng.sgdata.data.network.di

import com.liuyuheng.sgdata.data.network.DataStoreSearchApi
import com.liuyuheng.sgdata.data.network.WeatherForecastApi
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
        @DataStoreSearchEndpoint retrofit: Retrofit,
    ): DataStoreSearchApi = retrofit.create(DataStoreSearchApi::class.java)
}