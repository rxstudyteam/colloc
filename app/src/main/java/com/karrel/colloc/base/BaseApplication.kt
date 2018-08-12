package com.karrel.colloc.base

import android.app.Application
import android.preference.PreferenceManager
import com.eastandroid.mlb_base.PP

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PP.CREATE(applicationContext)
        createChannel()
    }


    private fun createChannel() {
        // 채널 생성
        val versionCode = PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("VERSION_CODE", 0)
        val currentVersionCode = packageManager.getPackageInfo(packageName, 0).versionCode
        if (versionCode < currentVersionCode) {
//            NotificationChannelManager.createChannel(applicationContext)
//            NotificationChannelManager.deleteChannel(applicationContext)
        }
    }
}