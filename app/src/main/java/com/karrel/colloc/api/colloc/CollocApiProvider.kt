package com.karrel.colloc.api.colloc

import com.karrel.colloc.api.CollocRetrofitClient
import com.karrel.colloc.api.loadGlobalTime.model.ErrorInfo
import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


object CollocApiProvider {
    ////http://karrel84.cafe24.com/colloc/airdata?tmX=244148.546388&tmY=412423.75772
    private const val BASE_URL = "http://karrel84.cafe24.com/"

    private val apis: CollocApiService

    init {
        apis = CollocRetrofitClient.create(BASE_URL, CollocApiService::class.java)
    }

    //NOTE sort 는 쿼리 테스트를 위하여 추가 한 것이라 리스폰스에 영항이 없음.
    //NOTE param 에 currentTime 이외의 값이 들어오면 json으로 리스폰스가 들어옵니다.
//    fun getAirdata(param: String = "currentTime", query: String = "sort", onLoaded: (time: String) -> Unit, onError: (error: ErrorInfo) -> Unit): Disposable {
    fun getAirdata(tmY: String = "0", tmX: String = "0",onLoaded: (data: AirData) -> Unit, onError: (error: ErrorInfo) -> Unit): Disposable {
        return getAirDataObservable(tmY, tmX,scheduler = Schedulers.io())
                .subscribe(
                        onLoaded
                ) { t ->
                    if (t is HttpException) {
                        //NOTE http response 가 200이 아닌 경우 여기로 온다고 합니다.
                        onError(ErrorInfo(t.message(), t.code().toString()))
                    } else {
                        onError(ErrorInfo(t.localizedMessage))
                    }
                }
    }

    // RX api Test 를 위해서 분리하였음
    fun getAirDataObservable(tmY: String = "currentTime", tmX: String = "sort", scheduler: Scheduler = AndroidSchedulers.mainThread()): Observable<AirData> {

        return apis.getAirData(tmY,tmX)
                .subscribeOn(scheduler)

//        return apis.getCurrentTime(param, query).subscribeOn(Schedulers.io())
//                .observeOn(scheduler) // fixme 안드로이드 main thread 로 호출하면 error 가 발생
//                .map { body ->
//                    when (body.contentType()?.subtype()) {
//                        "xml" -> throw Exception(Persister().read(ErrorInfo::class.java, body.string()).toString())
//                        "json" -> throw Exception(Gson().fromJson(body.string(), ErrorInfo::class.java).toString())
//                        "plain" -> body.string()
//                        else -> throw Exception("Other Type Contents : " + body.contentType())
//                    }
//                }
    }
}