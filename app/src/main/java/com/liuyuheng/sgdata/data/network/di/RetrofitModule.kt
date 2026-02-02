package com.liuyuheng.sgdata.data.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val DATA_GOV_REAL_TIME_DATA_URL = "https://api-open.data.gov.sg/v2/real-time/api/"
private const val DATA_GOV_DATA_STORE_SEARCH_URL = "https://data.gov.sg/api/action/datastore_search/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            },
        ).connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    @RealTimeEndpoint
    fun provideRealTimeRetrofit(
        client: OkHttpClient,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(DATA_GOV_REAL_TIME_DATA_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    @Singleton
    @Provides
    @DataStoreSearchEndpoint
    fun provideDataStoreSearchRetrofit(
        client: OkHttpClient,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(DATA_GOV_DATA_STORE_SEARCH_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()
}
