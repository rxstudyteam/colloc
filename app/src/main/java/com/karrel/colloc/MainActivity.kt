package com.karrel.colloc

import android.os.Bundle
import android.util.Log
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.loadGlobalTime.LoadGlobalTime

private  const val TAG = "dlwlrma"

class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = emptyList()
    override val layoutResID: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something
    }


    override val initView: () -> Unit = {
        val disposable = LoadGlobalTime.load(
                onLoaded = { time -> Log.d(TAG, "Load Global Time : $time") },
                onError = { info -> Log.d(TAG, "Load Fail :  $info") }
        )
        disposables.add(disposable)
    }
}
