package com.karrel.colloc.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.karrel.colloc.extensions.FragmentDisposable
import com.karrel.colloc.viewmodel.MainViewmodel

abstract class PartView(context: Context?, val viewModel: MainViewmodel, val disposable: FragmentDisposable) : LinearLayout(context) {

    init {
        addView(LayoutInflater.from(context).inflate(layoutRes(), this, false))

        setupButtonsEvents()
        setupObservableEvents()
    }

    protected open fun setupButtonsEvents() {}

    protected open fun setupObservableEvents() {}

    abstract fun layoutRes(): Int
    
}
