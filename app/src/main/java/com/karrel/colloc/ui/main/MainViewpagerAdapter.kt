package com.karrel.colloc.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class MainViewpagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val itemList: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return MainFragment.newInstance(location = itemList[position])
    }

    override fun getCount(): Int {
        return itemList.size
    }

    fun addLocation(location: String) {
        itemList.add(location)
        notifyDataSetChanged()
    }

}