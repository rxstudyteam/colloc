package com.karrel.colloc.notification

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import java.util.*

class NotificationUtil {

    fun setNotification(timeInMilliSeconds: Long, activity: Activity) {

        val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java)

        alarmIntent.putExtra("reason", "notification")
        alarmIntent.putExtra("timestamp", timeInMilliSeconds)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMilliSeconds

        val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 60000, pendingIntent)

    }

}