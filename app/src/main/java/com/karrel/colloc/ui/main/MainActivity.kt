package com.karrel.colloc.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.karrel.colloc.R
import com.karrel.colloc.api.loadGlobalTime.NaverGlobalAPIProvider
import com.karrel.colloc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    val TAG = this.javaClass.simpleName

    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")
    override val layoutResID: Int = R.layout.activity_main
    private lateinit var adapter: MainViewpagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Receive message!", Toast.LENGTH_LONG).show()
        setupViewPager()
    }

    private fun setupViewPager() {
        adapter = MainViewpagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        adapter.addFragment(MainFragment.newInstance(location = "긴고랑로 1길 20"))
        adapter.addFragment(MainFragment.newInstance(location = "강릉시 강문동"))
        adapter.addFragment(MainFragment.newInstance(location = "가평군 복면"))
        adapter.addFragment(MainFragment.newInstance(location = "청주시 신봉동"))
        adapter.addFragment(MainFragment.newInstance(location = "미국 LA"))
    }


    override val initView: () -> Unit = {
        val disposable = NaverGlobalAPIProvider.getCurrentTime(
                onLoaded = { time -> Log.d(TAG, "Load Global Time : $time") },
                onError = { info -> Log.d(TAG, "Load Fail :  $info") }
        )
        disposables.add(disposable)
    }

}
