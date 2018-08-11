package com.karrel.colloc.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.JobIntentService
import android.widget.RemoteViews
import com.karrel.colloc.NoahMainActivity
import com.karrel.colloc.R

class AlarmService : JobIntentService() {

    private lateinit var notification: Notification

    /**
     * 작업 큐에서 dequeue한 처리 작업을 전달 받음
     */
    @SuppressLint("NewApi")
    override fun onHandleWork(intent: Intent) {

        // TODO 데이터 로드
        // 서버 or 로컬로부터 업데이트된 데이터를 가져오기
        val location = intent?.extras?.getString("location")

        // 더미 데이터
        val title = "(미세미세) 청담동"
        val info = "좋음 - 신선한 공기 많이 마시세요~"
        val time = "2018-07-28 \n 오전09:00"

        val context = this.applicationContext
        var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifyIntent = Intent(this, NoahMainActivity::class.java)

        notifyIntent.putExtra("title", title)
        notifyIntent.putExtra("message", info)
        notifyIntent.putExtra("time", time)

        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val remoteViews = RemoteViews(context.packageName, R.layout.notification)
        remoteViews.setImageViewResource(R.id.ivNotifImage, R.drawable.emoticon_eight_level_small_1)
        remoteViews.setTextViewText(R.id.tvNotiTitle, title)
        remoteViews.setTextViewText(R.id.tvNotiInfo, info)
        remoteViews.setTextViewText(R.id.tvNotiTime, time)

        val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        val res = this.resources
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = Notification.Builder(this, NotificationChannelManager.eChannel.GENERAL.id)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setCustomContentView(remoteViews)
                    .build()
        } else {
            notification = Notification.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setCustomContentView(remoteViews)
                    .setSound(uri)
                    .build()
        }

        notificationManager.notify(NOTIFICATION_ID, notification)
    }


    companion object {
        const val NOTIFICATION_ID = 1000
        const val JOB_ID = 100

        // 실행할 동작을 작업 큐에 추가
        fun enqueueWork(context: Context, work: Intent)
                = enqueueWork(context, AlarmService::class.java, JOB_ID, work)
    }

}