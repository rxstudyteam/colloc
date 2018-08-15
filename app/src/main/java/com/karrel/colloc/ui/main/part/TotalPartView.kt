package com.karrel.colloc.ui.main.part

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.OverallValue
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

    //TODO overallData 이외에 전체 뷰에서 사용되는 ViewModel의 다른 data를 어떻게 access 할지 고민
    override fun setupButtonsEvents() {
        screenShare.setOnClickListener { overallData?.let{ Toast.makeText(context, "ScreenShare", Toast.LENGTH_SHORT).show() } }
        showForecast.setOnClickListener { overallData?.let{ Toast.makeText(context, "ShowForecast", Toast.LENGTH_SHORT).show() } }
    }
}