package com.karrel.colloc.ui.current

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.karrel.colloc.R
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.part_current.view.*


class CurrentPartView(context: Context?, viewModel: MainViewmodel) : PartView(context, viewModel) {

    override fun layoutRes(): Int = R.layout.part_current


    private lateinit var adapter: CurrentAdapter

    init {
        setupRecyclerView()
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
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = CurrentAdapter()
        recyclerView.adapter = adapter
    }

}
