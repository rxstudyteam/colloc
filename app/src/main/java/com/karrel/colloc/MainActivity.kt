package com.karrel.colloc

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.karrel.colloc.api.colloc.CollocApiProvider
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.notification.NotificationUtil
import java.util.*

class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")

    override val layoutResID: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something
        Toast.makeText(this, "Receive message!", Toast.LENGTH_LONG).show()

        registerAlarm("청담동", 3600000)
        CollocApiProvider.getAirdata("AS244148.546388","412423.75772",{

        },{
            Log.d("dlwlrma", "LOAD ERROR $it")
        })
    }


    override val initView: () -> Unit = {
//        val disposable = NaverGlobalAPIProvider.getCurrentTime(
//                onLoaded = { time -> Log.d(TAG, "Load Global Time : $time") },
//                onError = { info -> Log.d(TAG, "Load Fail :  $info") }
//        )
//        disposables.add(disposable)



    }

    private fun registerAlarm(location: String, interval: Long) {
        NotificationUtil().setNotification(Calendar.getInstance().timeInMillis,
                location, interval, this@MainActivity)
    }
}
