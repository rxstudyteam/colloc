package com.karrel.colloc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.loadGlobalTime.NaverGlobalAPIProvider
import com.karrel.colloc.main.CollocMainFr
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private const val TAG = "dlwlrma"

class MainActivity : BaseActivity() {

    override val requestPermissionList: List<String> = listOf("android.permission.ACCESS_FINE_LOCATION")

    override val layoutResID: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something


        Toast.makeText(this, "Receive message!", Toast.LENGTH_LONG).show()
    }


    var adapter: MainActivity.DAdapter? = null

    override val initView: () -> Unit = {
        val disposable = NaverGlobalAPIProvider.getCurrentTime(
                onLoaded = { time -> Log.d(TAG, "Load Global Time : $time") },
                onError = { info -> Log.d(TAG, "Load Fail :  $info") }
        )
        disposables.add(disposable)

        setSupportActionBar(toolbar as Toolbar)

        pager.adapter = DAdapter()

        tabs.setupWithViewPager(pager)
    }


    inner class DAdapter : FragmentPagerAdapter(supportFragmentManager) {
        val FRAGMENTS: List<Fragment> = Arrays.asList(
                Fragment.instantiate(this@MainActivity, CollocMainFr::class.java.name)//
                , Fragment.instantiate(this@MainActivity, CollocMainFr::class.java.name)//
                , Fragment.instantiate(this@MainActivity, CollocMainFr::class.java.name)//
        )

        override fun getItem(position: Int): Fragment {
            return FRAGMENTS[position]
        }

        override fun getCount(): Int {
            return FRAGMENTS.size
        }

        private val TITLES = arrayOf<CharSequence>("Eastar1", "Eastar2", "Eastar3")
        override fun getPageTitle(position: Int): CharSequence? {
            return TITLES[position]
        }
    }
}
