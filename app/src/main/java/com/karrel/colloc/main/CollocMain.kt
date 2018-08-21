package com.karrel.colloc.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.eastandroid.mlb_base.PP
import com.google.android.gms.ads.MobileAds
import com.karrel.colloc.R
import com.karrel.colloc.alarm.AlarmSettingActivity
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.ui.location.LocationActivity
import kotlinx.android.synthetic.main.colloc_main.*
import kotlinx.android.synthetic.main.colloc_main_content.*
import java.util.*


class CollocMain : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")
    override val layoutResID: Int = R.layout.colloc_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        MobileAds.initialize(applicationContext,"ca-app-pub-1050589565701629~4947884556")//REAL
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713")//TEST

        Toast.makeText(this, "Receive message!", Toast.LENGTH_LONG).show()

        toolbar.title = ""
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override val initView: () -> Unit = {
        viewPager.adapter = MainViewpagerAdapter(supportFragmentManager)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.app_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.addLocation -> {
                startActivity(Intent(this@CollocMain, LocationActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.sendYourFriend -> {}
            R.id.usageOfWHO -> {}
            R.id.miseEightMode -> {}
            R.id.sendAdmin -> {}
            R.id.infoMise -> {}
            R.id.forecastImage -> {}
            R.id.MiseAlarm -> startActivity(Intent(this@CollocMain, AlarmSettingActivity::class.java))
            R.id.setting -> {}
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun setToolbarBackground(grade:Int) {
        toolbar.background.level = grade
    }

    //-------------------------------------------------------------------------
    internal inner class MainViewpagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        val itemList: ArrayList<Fragment> = ArrayList()
        init {
            PP.LOCATIONS.set(setOf("마포구,37.5615756,126.838603" , "부송동,35.976749396987046,126.99599512792346"))
            var locatons = PP.LOCATIONS.getStringSet().toTypedArray()
            locatons =  arrayOf("헌재위치,37.57811822520621,126.98479394671537") + locatons

            for (locaton in locatons.indices) {
                itemList += CollocMainFr().apply {
                    arguments = Bundle().apply {
                        putString(CollocMainFr.EXTRA.LOCATION, locatons.elementAt(locaton))
                        putInt(CollocMainFr.EXTRA.ITEM_COUNT, locatons.size)
                        putInt(CollocMainFr.EXTRA.POSITION, locaton)
                    }

//                    toolbarBackground = { grade -> setToolbarBackground(grade) }
                }
            }
        }

        override fun getItem(position: Int): Fragment {
            return itemList[position]
        }

        override fun getCount(): Int {
            return itemList.size
        }
    }
}
