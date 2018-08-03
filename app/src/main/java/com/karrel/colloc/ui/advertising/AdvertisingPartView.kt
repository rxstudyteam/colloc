package com.karrel.colloc.ui.advertising

import android.content.Context
import com.karrel.colloc.R
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.part_advertising.view.*


class AdvertisingPartView(context: Context?, viewModel: MainViewmodel) : PartView(context, viewModel) {

    override fun layoutRes(): Int = R.layout.part_advertising

    init {
        setupButtonsEvents()
    }

    override fun setupObservableEvents() {
        advertising.setOnClickListener { viewModel.input.showAdvertising() }
    }

}
