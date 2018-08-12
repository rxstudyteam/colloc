package com.karrel.colloc.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.widget.Toast
import com.karrel.colloc.R
import com.karrel.colloc.base.BaseActivity
import kotlinx.android.synthetic.main.colloc_main.*
import java.util.*


class CollocMain : BaseActivity() {
    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")
    override val layoutResID: Int = R.layout.colloc_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Receive message!", Toast.LENGTH_LONG).show()
    }

    override val initView: () -> Unit = {
        viewPager.adapter = MainViewpagerAdapter(supportFragmentManager)
    }

    internal inner class MainViewpagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        val itemList: ArrayList<Fragment> = ArrayList()

        init {
            itemList.add(Fragment.instantiate(this@CollocMain, CollocMainFr::class.java.name, Bundle().apply { putString(CollocMainFr.EXTRA.LOCATION, "마포") }))

//            var locatons = PP.LOCATIONS.getStringSet()
//            for (locaton in locatons) {
//                itemList.add(Fragment.instantiate(this@CollocMain, CollocMainFr::class.java.name, Bundle().apply { putString(CollocMainFr.EXTRA.LOCATION, locaton) }))
//            }
        }

        override fun getItem(position: Int): Fragment {
            return itemList[position]
        }

        override fun getCount(): Int {
            return itemList.size
        }
    }
}
