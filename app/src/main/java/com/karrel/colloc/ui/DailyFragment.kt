package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.fragment_current.*


class DailyFragment : Fragment() {

    private lateinit var adapter: DailyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupTestData()
    }

    private fun setupTestData() {
        adapter.initItem()
        adapter.addItem(DailyAdapter.DailyItem("월요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("월요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("월요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.EmptyItem())
        adapter.addItem(DailyAdapter.DailyItem("화요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("화요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("화요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.EmptyItem())
        adapter.addItem(DailyAdapter.DailyItem("수요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("수요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("수요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.EmptyItem())
        adapter.addItem(DailyAdapter.DailyItem("목요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("목요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.DailyItem("목요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(DailyAdapter.EmptyItem())
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = DailyAdapter()
        recyclerView.adapter = adapter
    }
}
