package com.karrel.colloc.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.databinding.CollocMainFrBinding
import kotlinx.android.synthetic.main.colloc_main_fr.*

class CollocMainFr : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var bb: CollocMainFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.colloc_main_fr, container, false)
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
        bb.vm = ViewModelProviders.of(this).get(CollocMainViewModel::class.java)
    }

    private fun onLoad() {

    }

    //-------------------------------------------------------------------------------------
    override fun onRefresh() {
        Handler().postDelayed({ swipe_layer.isRefreshing = false }, 5000)
    }
}

