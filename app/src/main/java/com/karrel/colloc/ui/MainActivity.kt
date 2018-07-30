package com.karrel.colloc.ui

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.karrel.colloc.R
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.loadGlobalTime.NaverGlobalAPIProvider
import com.karrel.colloc.viewmodel.MainViewmodel
import com.karrel.colloc.viewmodel.MainViewmodelImpl
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

private const val TAG = "dlwlrma"

class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")

    override val layoutResID: Int = R.layout.activity_main

    private val viewModel: MainViewmodel = MainViewmodelImpl

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Receive message!", Toast.LENGTH_LONG).show()
        setupViemodelEvents()
        setupDummyData()
    }

    private fun setupDummyData() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe {
                    viewModel.input.setLocation("광진구 중곡 1동")
                    viewModel.input.setTime("2018-07-29 07:15 PM")
                    viewModel.input.setTitleStatus("좋음")
                    viewModel.input.setStatus("좋은 공기 많이 마시세요~")
                }
    }

    override val initView: () -> Unit = {
        val disposable = NaverGlobalAPIProvider.getCurrentTime(
                onLoaded = { time -> Log.d(TAG, "Load Global Time : $time") },
                onError = { info -> Log.d(TAG, "Load Fail :  $info") }
        )
        disposables.add(disposable)
    }


    private fun setupViemodelEvents() {
        viewModel.output.toastObservable().subscribe { showToast(it) }
    }

    private fun showToast(message: String?) {
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
            toast?.setGravity(Gravity.CENTER, 0, 0)
        } else {
            toast?.setText(message)
        }

        toast?.show()
    }


}
