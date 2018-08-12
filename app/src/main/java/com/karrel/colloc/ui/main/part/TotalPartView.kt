package com.karrel.colloc.ui.main.part

import android.content.Context
import android.util.AttributeSet
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.OverallValue
import com.karrel.colloc.ui.model.MainItem
import kotlinx.android.synthetic.main.part_total.view.*


class TotalPartView : BasePartView<OverallValue> {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun layoutRes(): Int = R.layout.part_total

    private var overallData: OverallValue? = null
        set(value) {
            field = value
            updateView()
        }

    override fun onChanged(t: OverallValue?) {

        overallData = t
    }

    fun updateView() {
        overallData?.let {
            curLocationMini?.text = it.locationName
            time?.text = it.updateTime
            titleStatus?.text = it.status
            status?.text = it.description

        }
    }
}