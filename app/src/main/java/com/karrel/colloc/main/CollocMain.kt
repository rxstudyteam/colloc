package com.karrel.colloc.main

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
import com.karrel.colloc.base.BaseActivity
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
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //-------------------------------------------------------------------------
    internal inner class MainViewpagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        val itemList: ArrayList<Fragment> = ArrayList()

        init {
            PP.LOCATIONS.set(setOf("마포", "제주"))
            var locatons = PP.LOCATIONS.getStringSet()
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
