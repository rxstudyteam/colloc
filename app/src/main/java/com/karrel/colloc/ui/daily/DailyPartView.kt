package com.karrel.colloc.ui.daily

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.karrel.colloc.R
import com.karrel.colloc.extensions.FragmentDisposable
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import karrel.com.mvvmsample.extensions.plusAssign
import kotlinx.android.synthetic.main.part_daily.view.*


class DailyPartView(context: Context?, viewModel: MainViewmodel, disposable: FragmentDisposable) : PartView(context, viewModel, disposable) {
    override fun layoutRes(): Int = R.layout.part_daily
    private lateinit var adapter: KarrelDailyAdapter

    init {
        setupRecyclerView()
    }

    override fun setupObservableEvents() {
        super.setupObservableEvents()
        disposable += viewModel.output.dailyDataObservable().subscribe {
            setupTestData()
        }
    }

    private fun setupTestData() {
        adapter.initItem()
        adapter.addItem(KarrelDailyAdapter.DailyItem("월요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("월요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("월요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.EmptyItem())
        adapter.addItem(KarrelDailyAdapter.DailyItem("화요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("화요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("화요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.EmptyItem())
        adapter.addItem(KarrelDailyAdapter.DailyItem("수요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("수요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("수요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.EmptyItem())
        adapter.addItem(KarrelDailyAdapter.DailyItem("목요일 아침", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("목요일 점심", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.DailyItem("목요일 저녁", R.drawable.smile, "좋음"))
        adapter.addItem(KarrelDailyAdapter.EmptyItem())
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = KarrelDailyAdapter()
        recyclerView.adapter = adapter
    }
}
