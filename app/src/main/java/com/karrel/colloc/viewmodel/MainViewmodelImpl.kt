package com.karrel.colloc.viewmodel

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class MainViewmodelImpl : MainViewmodel {
    override val input: MainViewmodel.Input get() = Input()
    override val output: MainViewmodel.Output get() = Output()

    val toastObservable = PublishSubject.create<String>()
    val locationObservable = PublishSubject.create<String>()
    val timeObservable = PublishSubject.create<String>()
    val titleStatusObservable = PublishSubject.create<String>()
    val statusObservable = PublishSubject.create<String>()
    val currentLocationObservable = PublishSubject.create<Boolean>()

    val advertisingObservable = PublishSubject.create<String>()
    val bottomAdvertisingObservable = PublishSubject.create<String>()
    val currentDataObservable = PublishSubject.create<String>()
    val dailyDataObservable = PublishSubject.create<String>()
    val detailDataObservable = PublishSubject.create<String>()
    val intervalDataObservable = PublishSubject.create<String>()

    inner class Input : MainViewmodel.Input {

        override fun requestWeatherData(location: String?) {
            // 더미데이터 발행
            location?.let { setLocation(it) }
            setTime("2018-07-29 07:15 PM")
            setTitleStatus("좋음")
            setStatus("좋은 공기 많이 마시세요~")

            advertisingObservable.onNext("")
            bottomAdvertisingObservable.onNext("")
            currentDataObservable.onNext("")
            dailyDataObservable.onNext("")
            detailDataObservable.onNext("")
            intervalDataObservable.onNext("")
        }

        override fun setCurrantLocation(b: Boolean) {
            currentLocationObservable.onNext(b)
        }

        override fun setTitleStatus(s: String) {
            titleStatusObservable.onNext(s)
        }

        override fun setStatus(s: String) {
            statusObservable.onNext(s)
        }

        override fun showAdvertising() {
            toastObservable.onNext("clicked advertising")
        }

        override fun setTime(s: String) {
            timeObservable.onNext("($s)")
        }

        override fun setLocation(s: String) {
            locationObservable.onNext(s)
        }

        override fun showForecast() {
            toastObservable.onNext("clicked showForecast")
        }

        override fun scrrenShare() {
            toastObservable.onNext("clicked scrrenShare")
        }
    }

    inner class Output : MainViewmodel.Output {
        override fun advertisingObservable(): Observable<String> = advertisingObservable
        override fun bottomAdvertisingObservable(): Observable<String> = bottomAdvertisingObservable
        override fun currentDataObservable(): Observable<String> = currentDataObservable
        override fun dailyDataObservable(): Observable<String> = dailyDataObservable
        override fun detailDataObservable(): Observable<String> = detailDataObservable
        override fun intervalDataObservable(): Observable<String> = intervalDataObservable
        override fun toastObservable() = toastObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun locationObservable() = locationObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun timeObservable() = timeObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun titlStatusObservable() = titleStatusObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun statusObservable() = statusObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun currentLocationObservable() = currentLocationObservable.observeOn(AndroidSchedulers.mainThread())!!
    }

}