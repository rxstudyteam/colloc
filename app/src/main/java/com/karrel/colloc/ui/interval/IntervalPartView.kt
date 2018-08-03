package com.karrel.colloc.ui.interval

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.karrel.colloc.R
import com.karrel.colloc.extensions.FragmentDisposable
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.part_interval.view.*


class IntervalPartView(context: Context?, viewModel: MainViewmodel, disposable: FragmentDisposable) : PartView(context, viewModel, disposable) {

    override fun layoutRes(): Int = R.layout.part_interval
    private lateinit var adapter: IntervalAdapter


    init {
        setupRecyclerView()
        setupTestCurrentData()
    }

    private fun setupTestCurrentData() {
        adapter.initItem()
        adapter.addItem(IntervalAdapter.IntervalItem("오후 8시", R.drawable.smile, "좋음"))
        adapter.addItem(IntervalAdapter.IntervalItem("오후 9시", R.drawable.smile, "좋음"))
        adapter.addItem(IntervalAdapter.IntervalItem("오후 10시", R.drawable.smile, "좋음"))
        adapter.addItem(IntervalAdapter.IntervalItem("오후 11시", R.drawable.smile, "좋음"))
        adapter.addItem(IntervalAdapter.IntervalItem("오후 12시", R.drawable.smile, "좋음"))
        adapter.addItem(IntervalAdapter.IntervalItem("오전 1시", R.drawable.smile, "좋음"))
        adapter.addItem(IntervalAdapter.IntervalItem("오전 2시", R.drawable.smile, "좋음"))
        adapter.addItem(IntervalAdapter.IntervalItem("오전 3시", R.drawable.smile, "좋음"))
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = IntervalAdapter()
        recyclerView.adapter = adapter
    }
}
