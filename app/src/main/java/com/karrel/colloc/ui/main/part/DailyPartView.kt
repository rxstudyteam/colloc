package com.karrel.colloc.ui.main.part

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.DailyForecast
import com.karrel.colloc.ui.daily.KarrelDailyAdapter
import kotlinx.android.synthetic.main.part_current.view.*


class DailyPartView : BasePartView<List<DailyForecast>> {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun layoutRes(): Int = R.layout.part_daily
    private var adapter: DailyAdapter = DailyAdapter()

    init {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private var dailyForecateList: List<DailyForecast>? = null
        set(value) {
            field = value
            updateView()
        }

    override fun onChanged(t: List<DailyForecast>?) {

        dailyForecateList= t
    }

    fun updateView() {
        dailyForecateList?.let {
            adapter.setItems(it)
        }
    }
}