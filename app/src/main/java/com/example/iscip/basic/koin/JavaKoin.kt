
package com.example.iscip.basic.koin

import android.content.Context
import com.example.iscip.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

object JavaKoin {

    const val TAG = "JavaKoin"

    @JvmStatic
    fun startKoinApplication(context: Context) {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(context.applicationContext)
                modules(koinModule)
                printLogger()
            }
        }
    }

    @JvmStatic
    fun closeKoinApplication() {
        stopKoin()
    }
}
