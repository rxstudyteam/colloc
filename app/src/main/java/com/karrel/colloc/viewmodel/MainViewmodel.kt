package com.karrel.colloc.viewmodel

import io.reactivex.Observable

interface MainViewmodel {
    val input: Input
    val output: Output

    interface Input {
        fun scrrenShare()
        fun showForecast()
    }

    interface Output{
        fun toastObservable() : Observable<String>
    }
}