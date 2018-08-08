package com.karrel.colloc.alarm

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout

abstract class AlarmSettingLinearView(context: Context) : LinearLayout(context) {

    init {
        addView(LayoutInflater.from(context).inflate(layoutResource(), this, false))
        setupViewEvents()
    }

    protected open fun setupViewEvents() { }

    abstract fun layoutResource(): Int
}