package com.karrel.colloc.ui.total

import android.content.Context
import android.view.View
import com.karrel.colloc.R
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.part_total.view.*


class TotalPartView(context: Context?, viewModel: MainViewmodel) : PartView(context, viewModel) {

    override fun layoutRes(): Int = R.layout.part_total

    override fun setupObservableEvents() {
        viewModel.output.locationObservable().subscribe { curLocationMini.text = it }
        viewModel.output.timeObservable().subscribe { time.text = it }
        viewModel.output.titlStatusObservable().subscribe { titleStatus.text = it }
        viewModel.output.statusObservable().subscribe { status.text = it }

        viewModel.output.currentLocationObservable().subscribe {
            if (it) {
                curLocationMini.visibility = View.VISIBLE
            } else {
                curLocationMini.visibility = View.GONE
            }
        }
    }

    override fun setupButtonsEvents() {
        screenShare.setOnClickListener { viewModel.input.scrrenShare() }
        showForecast.setOnClickListener { viewModel.input.showForecast() }
    }
}