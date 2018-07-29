package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.viewmodel.MainViewmodelImpl
import kotlinx.android.synthetic.main.fragment_total.*

class TotalFragment : Fragment() {

    val viewModel = MainViewmodelImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_total, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonsEvents()
    }

    private fun setupButtonsEvents() {
        screenShare.setOnClickListener { viewModel.input.scrrenShare() }
        showForecast.setOnClickListener {  viewModel.input.showForecast()}
    }

}
