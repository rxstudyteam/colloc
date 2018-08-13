package com.karrel.colloc.ui.main.part

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.HourlyForecast
import kotlinx.android.synthetic.main.part_current.view.*


class IntervalPartView : BasePartView<List<HourlyForecast>> {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun layoutRes(): Int = R.layout.part_interval
    private var adapter: IntervalAdapter = IntervalAdapter()

    init {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    private var hourlyForecateList: List<HourlyForecast>? = null
        set(value) {
            field = value
            updateView()
        }

    override fun onChanged(t: List<HourlyForecast>?) {

        hourlyForecateList= t
    }

    fun updateView() {
        hourlyForecateList?.let {
            adapter.setItems(it)
        }
    }
}