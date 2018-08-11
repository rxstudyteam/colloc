package com.karrel.colloc.ui.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.karrel.colloc.api.DummyAirProvider
import com.karrel.colloc.model.airdata.AirData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign


class AirViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var airData: MutableLiveData<AirData> = MutableLiveData<AirData>()

    init {
        loadAirData()
    }

    fun getAirData(): LiveData<AirData> {
        return airData
    }

    private fun loadAirData() {
        disposables += DummyAirProvider.getAirData(onLoaded = { airData.value = it }, onError = {})
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}