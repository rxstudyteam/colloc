package com.karrel.colloc.base

import android.app.Application
import android.base.BApplication
import android.preference.PreferenceManager

import com.karrel.colloc.notification.NotificationChannelManager
import com.eastandroid.mlb_base.PP


class BaseApplication : BApplication() {
    override fun onCreate() {
        super.onCreate()
        PP.CREATE(applicationContext)
        android.log.Log.LOG = true
        createChannel()
    }


    private fun createChannel() {
        // 채널 생성
        val versionCode = PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("VERSION_CODE", 0)
        val currentVersionCode = packageManager.getPackageInfo(packageName, 0).versionCode
        if (versionCode < currentVersionCode) {
            NotificationChannelManager.createChannel(applicationContext)
        }
    }
}