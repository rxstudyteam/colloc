package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.viewmodel.MainViewmodelImpl
import kotlinx.android.synthetic.main.fragment_current.*


class CurrentFragment : Fragment() {

    val viewModel = MainViewmodelImpl
    private lateinit var adapter: CurrentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupButtonsEvents()
        setupTestCurrentData()
    }

    private fun setupTestCurrentData() {
        adapter.initItem()
        adapter.addItem(CurrentAdapter.CurrentItem("미세먼지", R.drawable.smile, "좋음", "16ppm"))
        adapter.addItem(CurrentAdapter.CurrentItem("초미세먼지", R.drawable.smile, "좋음", "12ppm"))
        adapter.addItem(CurrentAdapter.CurrentItem("이산화질소", R.drawable.smile, "좋음", "15ppm"))
        adapter.addItem(CurrentAdapter.CurrentItem("오존", R.drawable.smile, "좋음", "2ppm"))
        adapter.addItem(CurrentAdapter.CurrentItem("일산화탄소", R.drawable.smile, "좋음", "1356ppm"))
        adapter.addItem(CurrentAdapter.CurrentItem("홍홍홍홍", R.drawable.smile, "좋음", "32ppm"))
        adapter.addItem(CurrentAdapter.CurrentItem("으헹헹헹", R.drawable.smile, "좋음", "22ppm"))
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        adapter = CurrentAdapter()
        recyclerView.adapter = adapter
    }

    private fun setupButtonsEvents() {
    }

}
