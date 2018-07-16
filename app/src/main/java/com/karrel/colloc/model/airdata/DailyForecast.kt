package com.karrel.colloc.model.airdata

// 일별
data class DailyForecast(
        var title: String? = null, // "월요일 아침"
        var grade: Int = 0, // 등급 : 2
        var status: String? = null, // 상태 : 좋음
        var pm10Value: String? = null,  // 초미세먼지 : "16 ㎍/㎥"
        var pm25Value: String? = null // 미세먼지 : "5 ㎍/㎥"
)
