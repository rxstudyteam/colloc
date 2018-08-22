package com.karrel.colloc.main

import android.content.Intent
import android.log.Log
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.eastandroid.mlb_base.PP
import com.google.android.gms.ads.MobileAds
import com.karrel.colloc.R
import com.karrel.colloc.alarm.AlarmSettingActivity
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.ui.location.LocationActivity
import kotlinx.android.synthetic.main.colloc_main.*
import java.util.*


class CollocMain : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")
    override val layoutResID: Int = R.layout.colloc_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        MobileAds.initialize(applicationContext,"ca-app-pub-1050589565701629~4947884556")//REAL
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")//TEST
        navView.setNavigationItemSelectedListener(this)
    }

    fun onToggleDrawer(v: View) {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            drawer_layout.openDrawer(GravityCompat.START)
    }

    fun onStartLocationActivity(v: View) {
        startActivity(Intent(this@CollocMain, LocationActivity::class.java))
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
        menuInflater.inflate(R.menu.app_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.e(item.title)
        when (item.itemId) {
            R.id.sendYourFriend -> {
            }
            R.id.usageOfWHO -> {
            }
            R.id.miseEightMode -> {
            }
            R.id.sendAdmin -> {
            }
            R.id.infoMise -> {
            }
            R.id.forecastImage -> {
            }
            R.id.MiseAlarm -> startActivity(Intent(this@CollocMain, AlarmSettingActivity::class.java))
            R.id.setting -> {
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //-------------------------------------------------------------------------
    internal inner class MainViewpagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        val itemList: ArrayList<Fragment> = ArrayList()

        init {
            PP.LOCATIONS.set(setOf("마포구,37.5615756,126.838603", "부송동,35.976749396987046,126.99599512792346"))
            var locatons = PP.LOCATIONS.getStringSet().toTypedArray()
            locatons = arrayOf("헌재위치,37.57811822520621,126.98479394671537") + locatons

            for (locaton in locatons.indices) {
                itemList += CollocMainFr().apply {
                    arguments = Bundle().apply {
                        putString(CollocMainFr.EXTRA.LOCATION, locatons.elementAt(locaton))
                        putInt(CollocMainFr.EXTRA.ITEM_COUNT, locatons.size)
                        putInt(CollocMainFr.EXTRA.POSITION, locaton)
                    }
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
