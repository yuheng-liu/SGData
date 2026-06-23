package com.liuyuheng.sgdata.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var applicationScope: CoroutineScope

    override fun onTerminate() {
        super.onTerminate()
        applicationScope.cancel()
    }
}