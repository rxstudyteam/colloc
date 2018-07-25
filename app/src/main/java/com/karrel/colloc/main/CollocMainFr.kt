package com.karrel.colloc.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.databinding.CollocMainFrBinding


class CollocMainFr : Fragment() {
    private lateinit var bb: CollocMainFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.colloc_main_fr, container, false)
        return bb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoad()
    }

    private fun onLoad() {
        bb.vm = ViewModelProviders.of(this).get(CollocMainViewModel::class.java)
    }


}

