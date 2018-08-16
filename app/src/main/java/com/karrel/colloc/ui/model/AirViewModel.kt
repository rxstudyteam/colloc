package com.karrel.colloc.ui.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.karrel.colloc.api.DummyAirProvider
import com.karrel.colloc.model.airdata.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign


class AirViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var airData: MutableLiveData<AirData> = MutableLiveData()
    private var overallData: MutableLiveData<OverallValue> = MutableLiveData()
    private var currentValueList: MutableLiveData<List<CurrentValue>> = MutableLiveData()
    private var dailyForecast: MutableLiveData<List<DailyForecast>> = MutableLiveData()
    private var hourlyForecast: MutableLiveData<List<HourlyForecast>> = MutableLiveData()
    private var extraInformation: MutableLiveData<List<ExtraInformation>> = MutableLiveData()
    private var advertisementData: MutableLiveData<String> = MutableLiveData()  //TODO  광고 이미지 url, 클릭시 이동될 url을 보관할 data class 를 하나 만들어야함

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

    fun getCurrentValue(): LiveData<List<CurrentValue>>{
        return currentValueList
    }

    fun getHourlyValue(): LiveData<List<HourlyForecast>>{
        return hourlyForecast
    }

    fun getDailyValue(): LiveData<List<DailyForecast>>{
        return dailyForecast
    }

    fun getDetailValue(): LiveData<List<ExtraInformation>>{
        return extraInformation
    }

    fun getAdvertisementValue(): LiveData<String> {
        return advertisementData
    }

    private fun loadAirData() {
        disposables += DummyAirProvider.getAirData(
                onLoaded = {
                    airData.value = it
                    overallData.value = it.overallValue
                    currentValueList.value = it.currentValues
                    dailyForecast.value = it.dailyForecasts
                    hourlyForecast.value = it.hourlyForecasts
                    extraInformation.value = it.extraInformation
                },
                onError = {})

    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}