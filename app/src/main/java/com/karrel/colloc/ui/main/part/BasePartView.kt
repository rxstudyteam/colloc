package com.karrel.colloc.ui.main.part

import android.arch.lifecycle.Observer
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater

abstract class BasePartView<T> : ConstraintLayout, Observer<T> {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    abstract fun layoutRes(): Int
    protected open fun setupButtonsEvents() {}

    init {
        LayoutInflater.from(context).inflate(layoutRes(), this, true)
        setupButtonsEvents()
    }

}
