package com.liuyuheng.sgdata.di

import com.liuyuheng.sgdata.domain.repository.BusRepository
import com.liuyuheng.sgdata.domain.usecase.GetBusesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetBusesUseCase(busRepository: BusRepository): GetBusesUseCase {
        return GetBusesUseCase(busRepository)
    }
}