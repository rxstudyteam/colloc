package com.karrel.colloc.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.karrel.colloc.R
import com.karrel.colloc.extensions.FragmentDisposable
import com.karrel.colloc.ui.advertising.AdvertisingPartView
import com.karrel.colloc.ui.bottom_advertising.BottomAdvertisingPartView
import com.karrel.colloc.ui.current.CurrentPartView
import com.karrel.colloc.ui.daily.DailyPartView
import com.karrel.colloc.ui.detail.DetailPartView
import com.karrel.colloc.ui.interval.IntervalPartView
import com.karrel.colloc.ui.total.TotalPartView
import com.karrel.colloc.viewmodel.MainViewmodel
import com.karrel.colloc.viewmodel.MainViewmodelImpl
import karrel.com.mvvmsample.extensions.plusAssign
import kotlinx.android.synthetic.main.fragment_main.*


class KarrelMainFragment : Fragment() {
    private val ARG_PARAM_IS_CUR_LOC = "is current location"
    private val ARG_PARAM_LOC_NAME = "location"

    companion object {
        @JvmStatic
        fun newInstance(isCurLocation: Boolean = false, location: String = "") =
                KarrelMainFragment().apply {
                    arguments = Bundle().apply {
                        putBoolean(ARG_PARAM_IS_CUR_LOC, isCurLocation)
                        putString(ARG_PARAM_LOC_NAME, location)
                    }
                }
    }

    private val viewModel: MainViewmodel = MainViewmodelImpl()
    private var isCurLocation: Boolean = false // 현재위치 플러그
    private var location: String? = null // 위치 정보

    private val disposable = FragmentDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupArguments()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPartViews()
        setupObservableEvents()
    }

    override fun onResume() {
        super.onResume()

        viewModel.input.requestWeatherData(location)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

    private fun setupArguments() {
        arguments?.let {
            isCurLocation = it.getBoolean(ARG_PARAM_IS_CUR_LOC)
            location = it.getString(ARG_PARAM_LOC_NAME)
        }
    }

    private fun addPartViews() {
        partGroupForm.addView(TotalPartView(context, viewModel, disposable))
        partGroupForm.addView(CurrentPartView(context, viewModel, disposable))
        partGroupForm.addView(AdvertisingPartView(context, viewModel, disposable))
        partGroupForm.addView(IntervalPartView(context, viewModel, disposable))
        partGroupForm.addView(DailyPartView(context, viewModel, disposable))
        partGroupForm.addView(DetailPartView(context, viewModel, disposable))
        partGroupForm.addView(BottomAdvertisingPartView(context, viewModel, disposable))
    }

    private fun setupObservableEvents() {
        disposable += viewModel.output.toastObservable().subscribe { showToast(it) }
    }

    private fun showToast(message: String?) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}