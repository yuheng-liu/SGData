package com.liuyuheng.sgdata.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val OPEN_MOVIES_DATABASE_BASE_URL = "https://www.omdbapi.com/"

class RetrofitServiceBuilder {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val defaultClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .client(defaultClient)
            .baseUrl(OPEN_MOVIES_DATABASE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}