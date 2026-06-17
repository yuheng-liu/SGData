package com.liuyuheng.sgdata.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealTimeEndpoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatasetEndpoint