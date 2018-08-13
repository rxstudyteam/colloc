package com.karrel.colloc.ui.main.part

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.HourlyForecast
import kotlinx.android.synthetic.main.item_interval.view.*
import java.util.ArrayList

class IntervalAdapter : RecyclerView.Adapter<IntervalAdapter.CurrentViewHolder>() {

    private val itemList: ArrayList<HourlyForecast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        return CurrentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_interval, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    fun setItems(intervalItem: List<HourlyForecast>) {
        itemList.clear()
        itemList.addAll(intervalItem)
        notifyDataSetChanged()
    }


    class CurrentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time = itemView.time
        val status = itemView.status
        val emoticon = itemView.emoticon

        fun setData(item: HourlyForecast) {
            time.text = item.title
//            emoticon.setImageResource(item.emoticon)
            status.text = item.status
        }
    }
}