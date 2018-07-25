package com.karrel.colloc.frame

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.karrel.colloc.R


class FrameAdapter(private val list: MutableList<Frame> = mutableListOf<Frame>()) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        //NOTE  뭔가 패턴을 적용 할 방법을 고민해 보도록 한다.
        return when(viewType){
            TYPE.HORIZONTAL ->  HorizontalLineViewHolder(inflater.inflate(R.layout.item_horizontal_line,parent,false))
            TYPE.EMPTY ->  EmptyViewHolder(inflater.inflate(R.layout.item_empty,parent,false))
            TYPE.INFO -> InfoViewHolder(inflater.inflate(R.layout.item_info,parent,false))
            TYPE.LABEL -> LabelViewHolder(inflater.inflate(R.layout.item_label,parent,false))
            TYPE.DETAIL_ITEM -> DetailItemViewHolder(inflater.inflate(R.layout.item_detail,parent,false))
            TYPE.DAILY -> DailyItemViewHolder(inflater.inflate(R.layout.item_daily,parent,false))
            else -> TODO("Not implemented  $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type

    private fun getItem(position : Int) : Frame = list[position]

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(getItem(position))


}