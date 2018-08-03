package com.karrel.colloc.viewmodel

import io.reactivex.Observable

interface MainViewmodel {
    val input: Input
    val output: Output

    interface Input {

        fun scrrenShare()
        fun showForecast()
        fun setLocation(s: String)
        fun setTime(s: String)
        fun showAdvertising()
        fun setTitleStatus(s: String)
        fun setStatus(s: String)
        fun setCurrantLocation(b: Boolean)
        fun requestWeatherData(location: String?)
    }

    interface Output {
        fun toastObservable(): Observable<String>
        fun locationObservable(): Observable<String>
        fun timeObservable(): Observable<String>
        fun titlStatusObservable(): Observable<String>
        fun statusObservable(): Observable<String>
        fun currentLocationObservable(): Observable<Boolean>
        fun advertisingObservable(): Observable<String>
        fun bottomAdvertisingObservable(): Observable<String>
        fun currentDataObservable(): Observable<String>
        fun dailyDataObservable(): Observable<String>
        fun detailDataObservable(): Observable<String>
        fun intervalDataObservable(): Observable<String>
    }
}