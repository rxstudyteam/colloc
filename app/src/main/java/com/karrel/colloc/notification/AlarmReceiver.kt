package com.karrel.colloc.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(context != null && intent != null)
            AlarmService.enqueueWork(context, intent)
    }

}