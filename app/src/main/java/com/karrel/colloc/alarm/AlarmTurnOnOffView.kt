package com.karrel.colloc.alarm

import android.content.Context
import android.widget.Toast
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.alarm_turn_on_off.view.*

class AlarmTurnOnOffView(context: Context) : AlarmSettingLinearView(context) {
    override fun layoutResource() = R.layout.alarm_turn_on_off

    override fun setupViewEvents() {
        tbAlarmSettingSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(context, isChecked.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}