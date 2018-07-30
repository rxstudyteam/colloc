package com.karrel.colloc.mainpage

import android.os.Bundle
import com.karrel.colloc.R
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.model.airdata.AirData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : BaseActivity() {

    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")
    override val layoutResID: Int = R.layout.activity_main_page

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
    }

    override val initView: () -> Unit = {
        // currentGpsToLocation
        val location = "청담동"

        // load data from api server or database
        disposables.add(viewModel.getData(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateView))
    }

    private val viewModel by lazy { MainViewModel(DataModel()) }

    private fun updateView(data: AirData) {
        // TODO update view
        // 업데이트된 데이터를 찾고 데이터 수치에 따라 다른 화면이 나오도록
        tvCurrentLocation.text = "현재위치"
        tvCurrentTime.text = "2018-07-28 09:00 AM"
        tvCurrentState.text = "최고 좋음!"
        tvCurrentStateSub.text = "공기 상태 최고! 건강하세요!"
    }


}
