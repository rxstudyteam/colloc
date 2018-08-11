package com.karrel.colloc.api

import com.karrel.colloc.api.loadGlobalTime.model.ErrorInfo
import com.karrel.colloc.model.airdata.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException


object DummyAirProvider {

    fun getAirData(onLoaded: (airData: AirData) -> Unit, onError: (error: ErrorInfo) -> Unit): Disposable {
        return getAirDataObservable().subscribe({ airData -> onLoaded(airData) },
                { t ->
                    if (t is HttpException) {
                        //NOTE http response 가 200이 아닌 경우 여기로 온다고 합니다.
                        onError(ErrorInfo(t.message(), t.code().toString()))
                    } else {
                        onError(ErrorInfo(t.localizedMessage))
                    }
                })
    }

    fun getAirDataObservable(): Observable<AirData> {

        val overallValue = OverallValue("201030123", 2, "2", "11", "location name")
        val currentValueList = listOf<CurrentValue>(CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("초미세먼지", 2, "나쁨", "27 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"), CurrentValue("미세먼지", 1, "좋음", "17 ㎍/㎥"))
        val hourlValueList = listOf<HourlyForecast>(HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), HourlyForecast("오후10시", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"))
        val dailyValueList = listOf<DailyForecast>(DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"), DailyForecast("월요일 아침", 2, "좋음", "16 ㎍/㎥", "5 ㎍/㎥"))
        val extraInFormation = ExtraInformation(" 업데이트 시간, PM10 측정소 이름 등", "가평")
        val extraList = listOf(extraInFormation, extraInFormation, extraInFormation)
        val source = Observable.just(AirData(overallValue = overallValue, currentValues = currentValueList, hourlyForecasts = hourlValueList, dailyForecasts = dailyValueList, extraInformation = extraList))
        return source
    }


}