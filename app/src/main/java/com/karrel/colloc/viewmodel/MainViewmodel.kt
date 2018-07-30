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
    }

    interface Output{
        fun toastObservable() : Observable<String>
        fun locationObservable(): Observable<String>
        fun timeObservable(): Observable<String>
    }
}