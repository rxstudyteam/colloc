package com.karrel.colloc.ui.main.part

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.CurrentValue
import kotlinx.android.synthetic.main.item_current.view.*
import java.util.ArrayList

class CurrentAdapter : RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder>() {

    private var itemList: ArrayList<CurrentValue> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        return CurrentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_current, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    fun setItems(currentItem: List<CurrentValue>) {
        itemList.clear()
        itemList.addAll(currentItem)
        notifyDataSetChanged()
    }


    class CurrentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val type = itemView.type
        val emoticon = itemView.emoticon
        val status = itemView.status
        val data = itemView.data


        fun setData(item: CurrentValue) {
            type.text = item.title
//            emoticon.setImageResource(item)
            status.text = item.status
            data.text = item.value
        }
    }

    class CurrentItem(val type: String = "미세먼지", val emoticon: Int = R.drawable.smile, val status: String = "좋음", val data: String = "0.015 ppm")

}