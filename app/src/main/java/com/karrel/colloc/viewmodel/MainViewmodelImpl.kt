package com.karrel.colloc.viewmodel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

object MainViewmodelImpl : MainViewmodel {
    override val input: MainViewmodel.Input get() = Input()
    override val output: MainViewmodel.Output get() = Output()

    val toastObservable = PublishSubject.create<String>()
    val locationObservable = PublishSubject.create<String>()
    val timeObservable = PublishSubject.create<String>()
    val titlStatusObservable = PublishSubject.create<String>()
    val statusObservable = PublishSubject.create<String>()

    class Input : MainViewmodel.Input {
        override fun setTitleStatus(s: String) {
            titlStatusObservable.onNext(s)
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

    class Output : MainViewmodel.Output {
        override fun toastObservable() = toastObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun locationObservable() = locationObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun timeObservable() = timeObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun titlStatusObservable() = titlStatusObservable.observeOn(AndroidSchedulers.mainThread())!!
        override fun statusObservable() = statusObservable.observeOn(AndroidSchedulers.mainThread())!!
    }

}