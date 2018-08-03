package com.karrel.colloc.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class FragmentDisposable(private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {
    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun clear() {
        compositeDisposable.clear()
    }
}