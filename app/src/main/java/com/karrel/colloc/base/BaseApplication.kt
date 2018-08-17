package com.karrel.colloc.base

import android.app.Application
import android.base.BApplication
import android.preference.PreferenceManager

import com.karrel.colloc.notification.NotificationChannelManager
import com.eastandroid.mlb_base.PP
import com.google.android.gms.ads.MobileAds


class BaseApplication : BApplication() {
    override fun onCreate() {
        super.onCreate()
        PP.CREATE(applicationContext)
        android.log.Log.LOG = true
        createChannel()


//        MobileAds.initialize(applicationContext,"ca-app-pub-1050589565701629~4947884556")//REAL
        MobileAds.initialize(applicationContext,"ca-app-pub-3940256099942544~3347511713")//TEST
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