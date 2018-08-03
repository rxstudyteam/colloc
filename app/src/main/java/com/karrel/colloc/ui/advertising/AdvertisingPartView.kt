package com.karrel.colloc.ui.advertising

import android.content.Context
import android.view.View
import com.karrel.colloc.R
import com.karrel.colloc.extensions.FragmentDisposable
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import karrel.com.mvvmsample.extensions.plusAssign
import kotlinx.android.synthetic.main.part_advertising.view.*


class AdvertisingPartView(context: Context?, viewModel: MainViewmodel, disposable: FragmentDisposable) : PartView(context, viewModel, disposable) {

    override fun layoutRes(): Int = R.layout.part_advertising

    override fun setupButtonsEvents() {
        advertising.setOnClickListener { viewModel.input.showAdvertising() }
    }

    override fun setupObservableEvents() {
        disposable += viewModel.output.advertisingObservable().subscribe {
            // 광고 설정
        }
    }
}
