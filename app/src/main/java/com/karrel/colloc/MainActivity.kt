package com.karrel.colloc

import android.os.Bundle
import android.view.View
import com.karrel.colloc.base.BaseActivity
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;



class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = emptyList()


    override val layoutResID: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something
        Fabric.with(this, Crashlytics())

        // TODO: Move this to where you establish a user session
        logUser();


    }

    override val initView: () -> Unit = {

    }

    private fun logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345")
        Crashlytics.setUserEmail("user@fabric.io")
        Crashlytics.setUserName("Test User")
    }


    fun forceCrash(view: View) {
        throw RuntimeException("This is a crash")
    }

}
