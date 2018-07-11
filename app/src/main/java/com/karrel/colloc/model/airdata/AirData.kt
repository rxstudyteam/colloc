package com.karrel.colloc.model.airdata

import java.util.ArrayList

class AirData {
    var overallValue = OverallValue()
    var currentValues: List<CurrentValue> = ArrayList()
    var hourlyForecasts: List<HourlyForecast> = ArrayList()
    var dailyForecasts: List<DailyForecast> = ArrayList()
    var extraInformations: List<ExtraInformation> = ArrayList()
    var note = "콜록콜록은 사용자분과 가장 가까이 위치한, 정상 작동 중인 측정소의 실시간 정보를 보여드립니다.\n\n본 자료는 한국환경공단(에어코리아)과 기상청에서 제공하는 실시간 관측 자료이며 실제 대기농도 수치와 다를 수 있습니다."

}
