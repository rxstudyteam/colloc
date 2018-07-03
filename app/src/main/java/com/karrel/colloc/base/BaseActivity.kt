package com.karrel.colloc.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity() : AppCompatActivity() {


    abstract val requestPermissionList: List<String>
    abstract val initView: () -> Unit


    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        if (requestPermissionList.isNotEmpty()) {
            requestPermission(initView)
        } else {
            initView()
        }
    }

    fun requestPermission(initView: () -> Unit) {

    }


}