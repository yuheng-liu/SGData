package com.liuyuheng.sgdata.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealTimeEndpoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatasetDownloadEndpoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataV1ApiEndpoint