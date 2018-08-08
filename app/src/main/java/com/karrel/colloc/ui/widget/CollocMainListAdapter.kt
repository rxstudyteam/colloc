package com.karrel.colloc.ui.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.ui.model.MainItem
import com.karrel.colloc.ui.model.MainViewType
import kotlinx.android.synthetic.main.listrow_main_current.view.*
import kotlinx.android.synthetic.main.listrow_main_daily.view.*
import kotlinx.android.synthetic.main.listrow_main_extra.view.*
import kotlinx.android.synthetic.main.listrow_main_header.view.*
import kotlinx.android.synthetic.main.listrow_main_hourly.view.*


/**
 * Created by Noah.Kim on 2017. 8. 7..
 */

class CollocMainListAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = this::class.java.simpleName

    var dataModelList: List<MainItem>? = null

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {


        when (viewHolder) {
            is HeaderViewHolder -> {
                val item = dataModelList?.get(position) as? MainItem.headItem
                item?.let { data ->
                    viewHolder.location.text = data.location
                    viewHolder.time.text = data.time.toString()
                    viewHolder.state.text = data.airState.toString()
                    viewHolder.description.text = data.airDescription
                }
            }

            is CurrentViewHolder -> {
                val item = dataModelList?.get(position) as? MainItem.currentItem
                item?.let { item ->
                    viewHolder.currentListAdapter.setArrayList(item.currentValueList)
                }
            }

            is HourlyViewHolder -> {
                val item = dataModelList?.get(position) as? MainItem.hourlyItem
                item?.let { item ->
                    viewHolder.hourlyListAdapter.setArrayList(item.hourlyForecastList)
                }
            }

            is DailyViewHolder -> {
                val item = dataModelList?.get(position) as? MainItem.dailyItem
                item?.let { item ->
                    viewHolder.dailyListAdapter.setArrayList(item.dailyForecastList)
                }
            }

                is ExtraInformationViewHolder -> {
                    val item = dataModelList?.get(position) as? MainItem.extraItem
                    item?.let {
                        item ->
                        viewHolder.extraTitle.text =item.extraInfomation.title
                        viewHolder.extraValue.text =item.extraInfomation.value
                    }
                }

            }
        }

        override fun getItemViewType(position: Int): Int {
            return dataModelList?.get(position)?.viewType?.ordinal ?: 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


            when (viewType) {
                MainViewType.Header.ordinal -> {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.listrow_main_header, parent, false)
                    return HeaderViewHolder(view)
                }
                MainViewType.Current.ordinal -> {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.listrow_main_current, parent, false)
                    return CurrentViewHolder(view)
                }
                MainViewType.Hourly.ordinal -> {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.listrow_main_hourly, parent, false)
                    return HourlyViewHolder(view)
                }
                MainViewType.Daily.ordinal -> {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.listrow_main_daily, parent, false)
                    return DailyViewHolder(view)
                }

                MainViewType.Extra.ordinal -> {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.listrow_main_extra, parent, false)
                    return ExtraInformationViewHolder(view)
                }

            }
            throw RuntimeException("there is no type that matches the type $viewType + make")
        }

        override fun getItemCount(): Int {
            return dataModelList.orEmpty().size
        }


        inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val location = itemView.header_location
            val time = itemView.header_time
            val state = itemView.header_state
            val description = itemView.header_description
        }

        inner class CurrentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            private val currentList: RecyclerView = itemView.current_list
            val currentListAdapter: CurrentInfoListAdapter by lazy { CurrentInfoListAdapter(context) }

            init {
                currentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                currentList.adapter = currentListAdapter
            }
        }

        inner class HourlyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            private val hourlyList: RecyclerView = itemView.hourly_list
            val hourlyListAdapter: HourlyInfoListAdapter by lazy { HourlyInfoListAdapter(context) }

            init {
                hourlyList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                hourlyList.adapter = hourlyListAdapter
            }
        }


        inner class DailyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val dailyList: RecyclerView = itemView.daily_list
            val dailyListAdapter: DailyInfoListAdapter by lazy { DailyInfoListAdapter(context) }

            init {
                dailyList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                dailyList.adapter = dailyListAdapter
            }
        }


        inner class ExtraInformationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val extraTitle = itemView.extra_title
            val extraValue = itemView.extra_value
        }

    }
