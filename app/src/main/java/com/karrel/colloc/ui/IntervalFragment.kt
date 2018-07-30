package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.viewmodel.MainViewmodel
import com.karrel.colloc.viewmodel.MainViewmodelImpl
import kotlinx.android.synthetic.main.fragment_current.*


class IntervalFragment : Fragment() {

    val viewModel: MainViewmodel = MainViewmodelImpl
    private lateinit var adapter: IntervalAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_interval, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupButtonsEvents()
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
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        adapter = IntervalAdapter()
        recyclerView.adapter = adapter
    }

    private fun setupButtonsEvents() {
    }

}
