package com.karrel.colloc.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class MainViewpagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val itemList: ArrayList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return itemList[position]
    }

    override fun getCount(): Int {
        return itemList.size
    }

    fun addFragment(fragment: MainFragment) {
        itemList.add(fragment)
        notifyDataSetChanged()
    }

}