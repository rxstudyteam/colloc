package com.karrel.colloc.ui.interval

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.item_interval.view.*
import java.util.ArrayList

class IntervalAdapter : RecyclerView.Adapter<IntervalAdapter.CurrentViewHolder>() {

    private val itemList: ArrayList<IntervalItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        return CurrentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_interval, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    fun addItem(intervalItem: IntervalItem) {
        itemList.add(intervalItem)
        notifyDataSetChanged()
    }

    fun initItem() {
        itemList.clear()
    }


    class CurrentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun setData(item: IntervalItem) {
            itemView.time.text = item.time
            itemView.emoticon.setImageResource(item.emoticon)
            itemView.status.text = item.status
        }
    }


    class IntervalItem(val time: String = "오후8시", val emoticon: Int = R.drawable.smile, val status: String = "좋음")
}