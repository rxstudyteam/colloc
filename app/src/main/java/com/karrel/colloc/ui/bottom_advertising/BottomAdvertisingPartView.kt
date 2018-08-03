package com.karrel.colloc.ui.bottom_advertising

import android.content.Context
import com.karrel.colloc.R
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.part_bottom_advertising.view.*


class BottomAdvertisingPartView(context: Context?, viewModel: MainViewmodel) : PartView(context, viewModel) {
    override fun layoutRes(): Int = R.layout.part_bottom_advertising

    override fun  setupButtonsEvents() {
        advertising.setOnClickListener { viewModel.input.showAdvertising() }
    }

}
