package com.karrel.colloc.main

import android.util.SDF
import com.karrel.colloc.model.airdata.*


fun dummyAirData(): AirData = AirData().apply {
    overallValue = OverallValue(SDF.yyyymmddhhmma_2.now(), 1, "좋음", "신선한 공기 많이 마시세요~", "광진구 중곡 1동")
    currentValues = listOf(
            CurrentValue("미세먼지", 2, "좋음", "7 ㎍/㎥"),
            CurrentValue("초미세먼저", 1, "좋음", "217 ㎍/㎥"),
            CurrentValue("이산화질서", 0, "좋음", "1 ㎍/㎥"),
            CurrentValue("오줌", 3, "좋음", "7 ㎍/㎥"),
            CurrentValue("일산환전소", 4, "좋음", "15 ㎍/㎥"),
            CurrentValue("아황산방귀", 6, "최고나쁨", "17 ㎍/㎥")
    )
    hourlyForecasts = listOf(
            HourlyForecast("오후11시", 0),
            HourlyForecast("오후12시", 1),
            HourlyForecast("오전1시", 1),
            HourlyForecast("오전2시", 2),
            HourlyForecast("오전3시", 3),
            HourlyForecast("오전4시", 1),
            HourlyForecast("오전5시", 4),
            HourlyForecast("오전6시", 5),
            HourlyForecast("오전7시", 1),
            HourlyForecast("오전8시", 2),
            HourlyForecast("오전9시", 3),
            HourlyForecast("오전10시", 4),
            HourlyForecast("오전11시", 2),
            HourlyForecast("오전12시", 1)
    )
    dailyForecasts = listOf(
            DailyForecast("월요일 아침", 1),
            DailyForecast("월요일 점심", 2),
            DailyForecast("월요일 저녁", 3),
            DailyForecast("화요일 아침", 1),
            DailyForecast("화요일 점심", 2),
            DailyForecast("월요일 저녁", 3),
            DailyForecast("화요일 아침", 1),
            DailyForecast("화요일 점심", 2),
            DailyForecast("월요일 저녁", 3),
            DailyForecast("화요일 아침", 1),
            DailyForecast("화요일 점심", 2),
            DailyForecast("월요일 저녁", 3),
            DailyForecast("화요일 아침", 1),
            DailyForecast("화요일 점심", 2),
            DailyForecast("화요일 저녁", 3)
    )
    extraInformation = listOf(
            ExtraInformation("업데이트시간", SDF.yyyymmddhhmm_1.now()),
            ExtraInformation("PM10 측정소 이름", "신촌로"),
            ExtraInformation("PM2.5 측정소 이름", "신촌로"),
            ExtraInformation("NO2 측정소 이름", "신촌로"),
            ExtraInformation("O3 측정소 이름", "신촌로"),
            ExtraInformation("CO 측정소 이름", "신촌로"),
            ExtraInformation("SO2 측정소 이름", "신촌로"),
            ExtraInformation("PM10 측정망 분류", "도로변대기"),
            ExtraInformation("PM2.5 측정망 분류", "도로변대기"),
            ExtraInformation("통합지수 값", "32 unit(최근 24시간 평균)"),
            ExtraInformation("통합지수 상태", "좋음(최근 24시간 평균)")
    )
}