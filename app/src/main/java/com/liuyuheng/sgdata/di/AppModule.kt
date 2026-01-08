package com.liuyuheng.sgdata.di

import com.liuyuheng.sgdata.presentation.shared.loader.LoadingStateHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoadingState(): LoadingStateHandler = LoadingStateHandler()
}