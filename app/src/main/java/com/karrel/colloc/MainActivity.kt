package com.karrel.colloc

import android.os.Bundle
import android.view.View
import com.karrel.colloc.base.BaseActivity
import android.view.ViewGroup
import android.widget.Button
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric





class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = emptyList()


    override val layoutResID: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something

//        val fabric = Fabric.Builder(this)
//                .kits(Crashlytics())
//                .debuggable(true)           // Enables Crashlytics debugger
//                .build()
//        Fabric.with(fabric)

        val crashButton = Button(this)
        crashButton.setText("Crash!")
        crashButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Crashlytics.getInstance().crash() // Force a crash
            }
        })
        addContentView(crashButton,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))

    }

    override val initView: () -> Unit = {

    }
}
