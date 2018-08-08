package com.karrel.colloc.mainpage

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import com.karrel.colloc.R
import com.karrel.colloc.alarm.AlarmSettingActivity
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.model.airdata.AirData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")
    override val layoutResID: Int = R.layout.activity_main_page
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        initLayout()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.sendYourFriend -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            R.id.usageOfWHO -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            R.id.miseEightMode -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            R.id.sendAdmin -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            R.id.infoMise -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            R.id.forecastImage -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            R.id.MiseAlarm -> startActivity(Intent(this@MainPageActivity, AlarmSettingActivity::class.java))
            R.id.setting -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return false
    }

    override fun onBackPressed() {
        if( drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
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

    private fun initLayout() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)

        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        navView.setNavigationItemSelectedListener(this)
    }

}
