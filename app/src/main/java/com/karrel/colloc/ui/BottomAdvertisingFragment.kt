package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.viewmodel.MainViewmodel
import com.karrel.colloc.viewmodel.MainViewmodelImpl
import kotlinx.android.synthetic.main.fragment_advertising.*
import kotlinx.android.synthetic.main.fragment_current.*


class BottomAdvertisingFragment : Fragment() {

    private val viewModel: MainViewmodel = MainViewmodelImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_advertising, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonsEvents()
    }

    private fun setupButtonsEvents() {
        advertising.setOnClickListener { viewModel.input.showAdvertising() }
    }

}
