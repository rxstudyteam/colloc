package com.karrel.colloc.notification

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import java.util.*

class NotificationUtil {

    fun setNotification(currentTime: Long, location: String, interval: Long, activity: Activity) {

        val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java)

        alarmIntent.putExtra("location", location)
        alarmIntent.putExtra("timestamp", currentTime)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTime

        val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, interval, pendingIntent)

    }

}