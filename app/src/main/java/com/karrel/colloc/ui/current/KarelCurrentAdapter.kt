package com.karrel.colloc.ui.current

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.item_current.view.*
import java.util.ArrayList

class KarelCurrentAdapter : RecyclerView.Adapter<KarelCurrentAdapter.CurrentViewHolder>() {

    private val itemList: ArrayList<CurrentItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        return CurrentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_current, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    fun addItem(currentItem: CurrentItem) {
        itemList.add(currentItem)
        notifyDataSetChanged()
    }

    fun initItem() {
        itemList.clear()
    }


    class CurrentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun setData(item: CurrentItem) {
            itemView.type.text = item.type
            itemView.emoticon.setImageResource(item.emoticon)
            itemView.status.text = item.status
            itemView.data.text = item.data
        }
    }


    class CurrentItem(val type: String = "미세먼지", val emoticon: Int = R.drawable.smile, val status: String = "좋음", val data: String = "0.015 ppm")
}