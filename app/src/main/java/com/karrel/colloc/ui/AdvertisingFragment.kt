package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.fragment_advertising.*


class AdvertisingFragment : Fragment(), Viewmodelable {
    private lateinit var viewModel: MainViewmodel

    override fun setViewmodel(viewmodel: MainViewmodel) {
        this.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_advertising, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonsEvents()
    }

    private fun setupButtonsEvents() {
        advertising.setOnClickListener { viewModel.input.showAdvertising() }
    }

}
