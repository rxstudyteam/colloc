package com.karrel.colloc.ui.main.part

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.DailyForecast
import kotlinx.android.synthetic.main.item_daily.view.*
import java.util.ArrayList

class DailyAdapter : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    private var itemList: ArrayList<DailyForecast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    fun setItems(currentItem: List<DailyForecast>) {
        itemList.clear()
        itemList.addAll(currentItem)
        notifyDataSetChanged()
    }


    class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val whenText = itemView.`when`
        val emoticon = itemView.emoticon
        val status = itemView.status

        fun setData(item: DailyForecast) {
            whenText.text = item.title
//            emoticon.setImageResource(item)
            status.text = item.status
        }
    }
}