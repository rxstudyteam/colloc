package com.karrel.colloc.viewmodel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

object MainViewmodelImpl : MainViewmodel {
    override val input: MainViewmodel.Input get() = Input()
    override val output: MainViewmodel.Output get() = Output()

    val toastObservable = PublishSubject.create<String>()

    class Input : MainViewmodel.Input {
        override fun showForecast() {
            toastObservable.onNext("clicked showForecast")
        }

        override fun scrrenShare() {
            toastObservable.onNext("clicked scrrenShare")
        }
    }

    class Output : MainViewmodel.Output {
        override fun toastObservable() = toastObservable.observeOn(AndroidSchedulers.mainThread())!!
    }

}