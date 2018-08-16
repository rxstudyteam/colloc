package android.base

import android.app.Application
import android.net.Net

open class BApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        var context = applicationContext
        Net.CREATE(context)
    }
}