package com.karrel.colloc.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.databinding.CollocMainFrBinding
import com.karrel.colloc.model.airdata.AirData
import kotlinx.android.synthetic.main.colloc_main_fr.*

class CollocMainFr : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    internal interface EXTRA {
        companion object {
            val LOCATION = "LOCATION"
        }
    }

    private var mLocation: String = ""
    private lateinit var bb: CollocMainFrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var location = savedInstanceState?.getString(EXTRA.LOCATION, "종로")
        mLocation = location ?: "종로"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.colloc_main_fr, container, false)
        bb.vm = ViewModelProviders.of(this).get(CollocMainViewModel::class.java)
        return bb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoadOnce()
        onLoad()
    }

    private fun onLoadOnce() {
        swipe_layer.setOnRefreshListener(this)
        swipe_layer.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark)
    }

    private fun onLoad() {

    }

    //-------------------------------------------------------------------------------------
    override fun onRefresh() {
//        Handler().postDelayed({ swipe_layer.isRefreshing = false }, 5000)
        bb.vm?.getAirData(mLocation)!!.observe(this, Observer { onUpdateUI(it) })

//        val long = 127.029475
//        val lan = 37.496690
//        bb.vm!!.getAirData(long, lan).observe(this, Observer {
//            it.toString()
//        })
        swipe_layer.isRefreshing = false
    }

    private fun onUpdateUI(airData: AirData?) {

    }
}

