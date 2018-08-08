package com.karrel.colloc.alarm

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.activity_alarm_setting.*

class AlarmSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_setting)

        initLayout()
        addView()
    }

    private fun initLayout() {
        toolbar.title = "알람/경보"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun addView() {
        llAlarmSettingRoot.addView(AlarmTurnOnOffView(this))
    }
}
