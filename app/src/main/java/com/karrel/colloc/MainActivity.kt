package com.karrel.colloc

import android.os.Bundle
import com.karrel.colloc.base.BaseActivity

class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")


    override val layoutResID: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something
    }

    override val initView: () -> Unit = {

    }
}
