package com.karrel.colloc.ui.detail

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.karrel.colloc.R
import com.karrel.colloc.extensions.FragmentDisposable
import com.karrel.colloc.ui.PartView
import com.karrel.colloc.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.part_detail.view.*


class DetailPartView(context: Context?, viewModel: MainViewmodel, disposable: FragmentDisposable) : PartView(context, viewModel, disposable) {
    override fun layoutRes(): Int = R.layout.part_detail

    private lateinit var adapter: DetailAdapter

    init {
        setupRecyclerView()
        setupTestData()
    }

    private fun setupTestData() {
        adapter.initItem()
        adapter.addItem(DetailAdapter.DetailItem("업데이트 시간:", "2018-07-29 19:00"))
        adapter.addItem(DetailAdapter.DetailItem("PM10 측정소 이름:", "송파구"))
        adapter.addItem(DetailAdapter.DetailItem("PM2.5 측정소 이름:", "광진구"))
        adapter.addItem(DetailAdapter.DetailItem("NO2 측정소 이름:", "광진구"))
        adapter.addItem(DetailAdapter.DetailItem("O3 측정소 이름:", "광진구"))
        adapter.addItem(DetailAdapter.DetailItem("CO 측정소 이름:", "광진구"))
        adapter.addItem(DetailAdapter.DetailItem("SO2 측정소 이름:", "광진구"))
        adapter.addItem(DetailAdapter.DetailItem("pm10 측정망 분류:", "도시대기"))
        adapter.addItem(DetailAdapter.DetailItem("pm2.5 측정망 분류:", "도시대기"))
        adapter.addItem(DetailAdapter.DetailItem("통합지수 값:", "53 unit(최근 24시간 평균)"))
        adapter.addItem(DetailAdapter.DetailItem("통합지수 상태:", "보통 (최근 24시간 평균)"))

        memo.text = "콜록은 사용자분과 가장 가까이 위치한, 정상 작동 중인\n측정소의 실시간 정보를 보여드립니다.\n\n본 자료는 한국환공공단과 블라블라블라 쇽쇽쇽 슥슥 샥샥"
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = DetailAdapter()
        recyclerView.adapter = adapter
    }
}
