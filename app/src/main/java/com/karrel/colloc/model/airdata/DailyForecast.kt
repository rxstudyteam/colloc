package com.karrel.colloc.model.airdata

import android.util.DT
import android.util.SDF
import java.text.SimpleDateFormat
import java.util.*

// 일별
data class DailyForecast(
        var title: String? = null, // "월요일 아침"
        var grade: Int = 0, // 등급 : 2
        var status: String? = null, // 상태 : 좋음
        var pm10Value: String? = null,  // 초미세먼지 : "16 ㎍/㎥"
        var pm25Value: String? = null // 미세먼지 : "5 ㎍/㎥"
)

data class 기간(var 시작: Int, var 끝: Int, var 이름: String)

fun 기간.between(hour: Int) = hour in 시작..(끝 - 1)

fun DailyForecast.getDate(): String {

    var day_of_week = DT.opt_format(title, SDF.yyyymmddhhmm_1.sdf, SDF.E__.sdf)

    var hour = DT.getTime(SDF.yyyymmddhhmm_1.opt_parse(title)) / (DT.DAY1 / 24)
    var hour3 = arrayOf(기간(0, 6, "저녁"),
            기간(6, 12, "아침"),
            기간(12, 18, "점심"),
            기간(18, 24, "저녁")
    ).forEach {
        if (it.between(hour.toInt())) return day_of_week + " " + it.이름
    }

    return day_of_week
}