package com.liuyuheng.sgdata.di

import com.liuyuheng.sgdata.data.repository.BusRepositoryImpl
import com.liuyuheng.sgdata.domain.repository.BusRepository
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
    fun provideBusRepository(): BusRepository {
        return BusRepositoryImpl()
    }
}
