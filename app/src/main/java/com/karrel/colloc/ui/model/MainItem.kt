package com.karrel.colloc.ui.model

import com.karrel.colloc.model.airdata.CurrentValue
import com.karrel.colloc.model.airdata.DailyForecast
import com.karrel.colloc.model.airdata.ExtraInformation
import com.karrel.colloc.model.airdata.HourlyForecast

sealed class MainItem(val viewType: MainViewType) {
    data class headItem(val location: String, val time: Long, val airState: Int, val airDescription: String) : MainItem(MainViewType.Header)
    data class currentItem(val currentValueList: List<CurrentValue>) : MainItem(MainViewType.Current)
    data class hourlyItem(val hourlyForecastList: List<HourlyForecast>) : MainItem(MainViewType.Hourly)
    data class dailyItem(val dailyForecastList: List<DailyForecast>) : MainItem(MainViewType.Daily)
    data class extraItem(val extraInfomation: ExtraInformation) : MainItem(MainViewType.Extra)
}

enum class MainViewType() {

    Header,
    Current,
    Hourly,
    Daily,
    Extra

}
