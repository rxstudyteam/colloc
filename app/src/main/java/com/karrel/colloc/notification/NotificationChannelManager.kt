package com.karrel.colloc.notification

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build

object NotificationChannelManager {

    enum class eChannel(val id: String, val channelName: String, val importance: Int, val description: String) {
        GENERAL("general"
                , "General"
                , NotificationManager.IMPORTANCE_DEFAULT
                , "Hello colloc channel general"),

        CollocNotification("CollocNotification"
                , "CollocChannel"
                , NotificationManager.IMPORTANCE_HIGH
                , "This is Colloc channel"),
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return

        val mn = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        run {
            val channel = NotificationChannel(eChannel.GENERAL.id, eChannel.GENERAL.name, eChannel.GENERAL.importance)
            channel.description = eChannel.GENERAL.description
            channel.enableVibration(true)
            channel.setShowBadge(true)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            mn.createNotificationChannel(channel)
        }
        run {
            val channel = NotificationChannel(eChannel.CollocNotification.id, eChannel.CollocNotification.name, eChannel.CollocNotification.importance)
            channel.description = eChannel.CollocNotification.description
            channel.enableVibration(true)
            channel.setShowBadge(false)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            mn.createNotificationChannel(channel)
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun deleteChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return

        val mn = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mn.deleteNotificationChannel("needToAdd")
    }

}