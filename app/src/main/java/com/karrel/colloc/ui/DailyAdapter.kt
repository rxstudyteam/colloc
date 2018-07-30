package com.karrel.colloc.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.item_daily.view.*
import java.util.ArrayList

class DailyAdapter : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {

    private val itemList: ArrayList<Item> = ArrayList()

    enum class ViewType {
        DAILY, EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ViewType.DAILY.ordinal) {
            DailyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false))
        } else {
            EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_daily_empty, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList[position] is DailyItem) {
            ViewType.DAILY.ordinal
        } else {
            ViewType.EMPTY.ordinal
        }
    }

    fun addItem(dailyItem: DailyAdapter.Item) {
        itemList.add(dailyItem)
        notifyDataSetChanged()
    }

    fun initItem() {
        itemList.clear()
    }


    abstract class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        abstract fun setData(item: Item)
    }

    class DailyViewHolder(itemView: View?) : ViewHolder(itemView) {
        override fun setData(item: DailyAdapter.Item) {
            itemView.`when`.text = item.time
            itemView.emoticon.setImageResource(item.emoticon)
            itemView.status.text = item.status
        }
    }

    class EmptyViewHolder(itemView: View?) : ViewHolder(itemView) {
        override fun setData(item: Item) {
        }
    }

    abstract class Item(val time: String = "워룡일 아침", val emoticon: Int = R.drawable.smile, val status: String = "좋음")

    class DailyItem(time: String, emoticon: Int, status: String) : Item(time, emoticon, status)

    class EmptyItem : Item()
}