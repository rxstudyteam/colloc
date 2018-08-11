package com.karrel.colloc.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.karrel.colloc.api.DummyAirProvider
import com.karrel.colloc.model.airdata.AirData


class AirViewModel : ViewModel() {
    private var airData: MutableLiveData<AirData> = MutableLiveData<AirData>()

    init {
        loadAirData()
    }

    fun getAirData(): LiveData<AirData> {
        return airData
    }

    private fun loadAirData() {
        val disposable = DummyAirProvider.getAirData(onLoaded = { airData.value = it }, onError = {})
        // fixme disposable 어디에서 처리할것인가?
    }
}