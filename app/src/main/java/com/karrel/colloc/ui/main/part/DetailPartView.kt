package com.karrel.colloc.ui.main.part

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.ExtraInformation
import kotlinx.android.synthetic.main.part_current.view.*


class DetailPartView : BasePartView<List<ExtraInformation>> {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun layoutRes(): Int = R.layout.part_detail
    private var adapter: DetailAdapter = DetailAdapter()

    init {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private var detailList: List<ExtraInformation>? = null
        set(value) {
            field = value
            updateView()
        }

    override fun onChanged(t: List<ExtraInformation>?) {

        detailList= t
    }

    fun updateView() {
        detailList?.let {
            adapter.setItems(it)
        }
    }
}