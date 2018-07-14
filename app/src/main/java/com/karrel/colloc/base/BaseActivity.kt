package com.karrel.colloc.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.eastandroid.lib.permission.PermissionRequest


abstract class BaseActivity() : AppCompatActivity() {


    abstract val requestPermissionList: List<String>
    abstract val initView: () -> Unit
    abstract val layoutResID:  Int



    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(layoutResID)
        super.onCreate(savedInstanceState)

        if (requestPermissionList.isNotEmpty()) {
            requestPermission(initView)
        } else {
            initView()
        }



    }

    fun requestPermission(initView: () -> Unit) {

        PermissionRequest(this, requestPermissionList).setGrentedListener(initView).run()
    }
}