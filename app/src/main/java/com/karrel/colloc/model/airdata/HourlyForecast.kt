package com.karrel.colloc.model.airdata

import android.util.DT
import android.util.SDF

//시간별
data class HourlyForecast(
        var title: String? = null, // "오후10시"
        var grade: Int = 0, // 등급 : 2
        var status: String? = null, // 상태 : 좋음
        var pm10Value: String? = null,  // 초미세먼지 : "16 ㎍/㎥"
        var pm25Value: String? = null // 미세먼지 : "5 ㎍/㎥"
)

fun HourlyForecast.getDate() = DT.opt_format(title, SDF.yyyymmddhhmm_1.sdf, SDF.ah__.sdf)