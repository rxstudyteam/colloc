package com.karrel.colloc.ui.main.part

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.ExtraInformation
import kotlinx.android.synthetic.main.item_detail.view.*
import java.util.*

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val itemList: ArrayList<ExtraInformation> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    fun setItems(extraInformation: List<ExtraInformation>) {
        itemList.clear()
        itemList.addAll(extraInformation)
        notifyDataSetChanged()
    }



    class DetailViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun setData(item: ExtraInformation) {
            itemView.title.text = item.title
            itemView.data.text = item.value
        }
    }

}