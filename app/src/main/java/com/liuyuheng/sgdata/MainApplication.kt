package com.liuyuheng.sgdata

import android.app.Application
import com.liuyuheng.sgdata.core.di.ApplicationScope
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

@HiltAndroidApp
class MainApplication : Application() {

    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    override fun onTerminate() {
        super.onTerminate()
        applicationScope.cancel()
    }
}