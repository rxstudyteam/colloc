package com.karrel.colloc

import android.os.Bundle
import android.widget.Toast
import com.karrel.colloc.base.BaseActivity

class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = emptyList()


    override val layoutResID: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something
        Toast.makeText(this, "Receive message!", Toast.LENGTH_LONG).show()
    }

    override val initView: () -> Unit = {

    }


}
