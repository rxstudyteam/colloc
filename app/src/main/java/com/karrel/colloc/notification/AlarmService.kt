package com.karrel.colloc.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.JobIntentService
import com.karrel.colloc.MainActivity
import com.karrel.colloc.R
import java.util.*

class AlarmService : JobIntentService() {

    private lateinit var notification: Notification

    /**
     * 작업 큐에서 dequeue한 처리 작업을 전달 받음
     */
    @SuppressLint("NewApi")
    override fun onHandleWork(intent: Intent) {
        // 채널 생성
        createChannel()

        val timestamp = intent?.extras?.getLong("timestamp")

        val context = this.applicationContext
        var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifyIntent = Intent(this, MainActivity::class.java)

        val title = "Colloc Notification"
        val message = "Colloc Message data"
        notifyIntent.putExtra("title", title)
        notifyIntent.putExtra("message", message)
        notifyIntent.putExtra("notification", true)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp ?: 0

        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        val res = this.resources
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = Notification.Builder(this, CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                            .bigText(message))
                    .setContentText(message)
                    .build()
        } else {
            notification = Notification.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSound(uri)
                    .build()
        }

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val context = this.applicationContext
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.description = CHANNEL_DESCRIPTION
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        const val CHANNEL_ID = "com.karrel.colloc.general"
        const val CHANNEL_NAME = "colloc general channel"
        const val CHANNEL_DESCRIPTION = "Hello Colloc channel"
        const val NOTIFICATION_ID = 1000
        const val JOB_ID = 100

        // 실행할 동작을 작업 큐에 추가
        fun enqueueWork(context: Context, work: Intent)
                = enqueueWork(context, AlarmService::class.java, JOB_ID, work)
    }

}