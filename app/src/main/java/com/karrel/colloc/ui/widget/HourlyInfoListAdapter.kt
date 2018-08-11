package com.karrel.colloc.ui.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.HourlyForecast
import kotlinx.android.synthetic.main.listrow_hourly_air_info.view.*

/**
 * @author Noah.Kim
 * on 2018. 8. 8..
 */
class HourlyInfoListAdapter(private val context: Context?) : RecyclerView.Adapter<HourlyInfoListAdapter.CurrentInfoViewHolder>() {

    private val TAG = HourlyInfoListAdapter::class.java.simpleName
    private var arrayList: List<HourlyForecast>? = null

    fun setArrayList(arrayList: List<HourlyForecast>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentInfoViewHolder {
        val mInflater = LayoutInflater.from(parent.context)
        val mainGroup = mInflater.inflate(R.layout.listrow_hourly_air_info, parent, false) as ViewGroup
        return CurrentInfoViewHolder(mainGroup)
    }

    override fun onBindViewHolder(mainHolder: CurrentInfoViewHolder, position: Int) {
        val currentValue = arrayList?.get(position)
        currentValue?.let {
            mainHolder.currentInfoTitle.text = it.title
//            mainHolder.currentInfoAirIcon = it.grade
            mainHolder.curerntInfoState.text = it.status
        }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) arrayList!!.size else 0
    }

    inner class CurrentInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var currentInfoTitle: TextView = itemView.hourly_info_title
        var currentInfoAirIcon: ImageView = itemView.hourly_info_air_icon
        var curerntInfoState: TextView = itemView.hourly_info_state
    }
}
