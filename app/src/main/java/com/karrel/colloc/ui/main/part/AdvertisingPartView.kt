package com.karrel.colloc.ui.main.part

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.part_bottom_advertising.view.*

class AdvertisingPartView : BasePartView<String> {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun layoutRes(): Int = R.layout.part_advertising

    //TODO 클릭시 activity 이동 또는 webview 형태 또는 외부 앱 실행 시키고 다시 앱으로 되돌아왔을 때를 어떻게 처리할지 .
    override fun setupButtonsEvents() {
        advertising.setOnClickListener { Toast.makeText(context, advertisingData, Toast.LENGTH_SHORT).show() }
    }

    private var advertisingData: String? = null

    override fun onChanged(t: String?) {
        advertisingData = t
    }
}