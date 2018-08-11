package com.karrel.colloc.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.model.airdata.CurrentValue
import com.karrel.colloc.model.airdata.DailyForecast
import com.karrel.colloc.model.airdata.ExtraInformation
import com.karrel.colloc.model.airdata.HourlyForecast
import com.karrel.colloc.ui.model.MainItem
import com.karrel.colloc.ui.widget.CollocMainListAdapter
import kotlinx.android.synthetic.main.fragment_main_noah.*

class NoahMainFragment : Fragment() {

    companion object {
        fun newInstance(): NoahMainFragment {
            return NoahMainFragment()
        }
    }

    private val collocMainListAdapter: CollocMainListAdapter by lazy { CollocMainListAdapter(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_noah, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        main_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        main_list.adapter = collocMainListAdapter

        // dummy data
        val currentValueList = listOf<CurrentValue>(CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("초미세먼지", 2, "나쁨", "27 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"))
        val hourlValueList = listOf<HourlyForecast>(HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"))
        val dailyValueList = listOf<DailyForecast>(DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"))
        val extraInFormation = ExtraInformation(" 업데이트 시간, PM10 측정소 이름 등", "가평")

        val mainItemList = mutableListOf<MainItem>()
        mainItemList.add(MainItem.headItem("분당구 삼평동", System.currentTimeMillis(), 0, "공기가 탁하네요. 조심하세요~"))
        mainItemList.add(MainItem.currentItem(currentValueList))
        mainItemList.add(MainItem.hourlyItem(hourlValueList))
        mainItemList.add(MainItem.dailyItem(dailyValueList))
        mainItemList.add(MainItem.extraItem(extraInFormation))

        collocMainListAdapter.dataModelList = mainItemList

    }
}