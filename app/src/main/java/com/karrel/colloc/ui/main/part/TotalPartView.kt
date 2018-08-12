package com.karrel.colloc.ui.main.part

import android.content.Context
import android.util.AttributeSet
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.OverallValue
import com.karrel.colloc.ui.model.MainItem
import kotlinx.android.synthetic.main.part_total.view.*


class TotalPartView: BasePartView<OverallValue>{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun layoutRes(): Int = R.layout.part_total

    private var overallData: MainItem.headItem? = null
        set(value) {
            field = value
            updateView()
        }

    override fun onChanged(t: OverallValue?) {
        overallData = t?.let {
            MainItem.headItem(it.locationName, it.updateTime, it.status, it.description)
        }
    }

    fun updateView(){
        overallData?.let {
            curLocationMini?.text = it.location
            time?.text = it.time
            titleStatus?.text = it.airState.toString()
            status?.text = it.airDescription

        }
    }
}