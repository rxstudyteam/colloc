package com.karrel.colloc.ui.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.karrel.colloc.api.DummyAirProvider
import com.karrel.colloc.model.airdata.AirData
import com.karrel.colloc.model.airdata.OverallValue
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign


class AirViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var airData: MutableLiveData<AirData> = MutableLiveData<AirData>()
    private var overallData: MutableLiveData<OverallValue> = MutableLiveData()

    init {
        loadAirData()
    }

    //fixme 여기를 여러개로 나누면 될거 같다.
    fun getAirData(): LiveData<AirData> {
        return airData
    }

    fun getOverallValue(): LiveData<OverallValue> {
        return overallData
    }

    private fun loadAirData() {
        disposables += DummyAirProvider.getAirData(
                onLoaded = {
                    airData.value = it
                    overallData.value = it.overallValue
                },
                onError = {})

    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}