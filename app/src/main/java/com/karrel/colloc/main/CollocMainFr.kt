package com.karrel.colloc.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.log.Log
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
            val POSITION = "POSITION"
            val ITEM_COUNT = "ITEM_COUNT"
        }
    }

    //        제주특별자치도 제주시 아라동 첨단로 242
    private lateinit var bb: CollocMainFrBinding

    private var mLocation: String = ""
    private var mPosition: Int = 0
    private var mItemCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(arguments)
        arguments?.run {
            mLocation = getString(EXTRA.LOCATION, "종로")
            mPosition = getInt(EXTRA.POSITION, -1)
            mItemCount = getInt(EXTRA.ITEM_COUNT, 0)
        }
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

        bb.indecater.setDrawable(R.drawable.shape_dot_n, R.drawable.shape_dot_c)
        bb.indecater.setCount(mItemCount)
        bb.indecater.setCurrentPosition(mPosition)
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

