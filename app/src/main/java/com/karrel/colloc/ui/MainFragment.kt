package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.karrel.colloc.R
import com.karrel.colloc.viewmodel.MainViewmodel
import com.karrel.colloc.viewmodel.MainViewmodelImpl
import kotlinx.android.synthetic.main.fragment_main.*

private const val ARG_PARAM_IS_CUR_LOC = "param1"
private const val ARG_PARAM_LOC_NAME = "param2"


class MainFragment : Fragment() {

    private val viewModel: MainViewmodel = MainViewmodelImpl()
    private var toast: Toast? = null

    private var isCurLocation: Boolean = false

    private var locationName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isCurLocation = it.getBoolean(ARG_PARAM_IS_CUR_LOC)
            locationName = it.getString(ARG_PARAM_LOC_NAME)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupDummyData()
        setupViemodelEvents()
    }

    private fun setupViewModel() {
        (totalFragment as? Viewmodelable)?.setViewmodel(viewModel)
        (currentFragment as? Viewmodelable)?.setViewmodel(viewModel)
        (advertisingFragment as? Viewmodelable)?.setViewmodel(viewModel)
        (intervalFragment as? Viewmodelable)?.setViewmodel(viewModel)
        (dailyFragment as? Viewmodelable)?.setViewmodel(viewModel)
        (detailFragment as? Viewmodelable)?.setViewmodel(viewModel)
        (bottomAdvertisingFragment as? Viewmodelable)?.setViewmodel(viewModel)
    }

    private fun setupDummyData() {
        viewModel.input.setCurrantLocation(isCurLocation)

        locationName?.let { viewModel.input.setLocation(it) }
        viewModel.input.setTime("2018-07-29 07:15 PM")
        viewModel.input.setTitleStatus("좋음")
        viewModel.input.setStatus("좋은 공기 많이 마시세요~")
    }

    private fun setupViemodelEvents() {
        viewModel.output.toastObservable().subscribe { showToast(it) }
    }

    private fun showToast(message: String?) {
        if (toast == null) {
            toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            toast?.setGravity(Gravity.CENTER, 0, 0)
        } else {
            toast?.setText(message)
        }

        toast?.show()
    }

    companion object {
        @JvmStatic
        fun newInstance(isCurLocation: Boolean = false, location: String = "") =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putBoolean(ARG_PARAM_IS_CUR_LOC, isCurLocation)
                        putString(ARG_PARAM_LOC_NAME, location)
                    }
                }
    }
}