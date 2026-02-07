package com.liuyuheng.sgdata.data.database.di

import android.content.Context
import androidx.room.Room
import com.liuyuheng.sgdata.data.database.SGDataDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SGDataDatabase = Room.databaseBuilder(
        context = context,
        klass = SGDataDatabase::class.java,
        name = "sgdata.db"
    ).build()

    @Provides
    fun provideCarparkInfoDao(
        database: SGDataDatabase
    ) = database.carparkInfoDao()

    @Provides
    fun provideMetadataDao(
        database: SGDataDatabase
    ) = database.metadataDao()
}