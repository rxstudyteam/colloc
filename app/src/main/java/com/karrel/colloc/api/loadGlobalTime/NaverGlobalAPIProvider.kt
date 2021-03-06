package com.karrel.colloc.api.loadGlobalTime

import com.google.gson.Gson
import com.karrel.colloc.api.CollocRetrofitClient
import com.karrel.colloc.api.loadGlobalTime.model.ErrorInfo
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.simpleframework.xml.core.Persister
import retrofit2.HttpException


object NaverGlobalAPIProvider {

    private const val BASE_URL = "https://global.apis.naver.com/";

    private val apis: NaverGlobalAPIService

    init {
        apis = CollocRetrofitClient.create(BASE_URL, NaverGlobalAPIService::class.java)
    }

    //NOTE sort 는 쿼리 테스트를 위하여 추가 한 것이라 리스폰스에 영항이 없음.
    //NOTE param 에 currentTime 이외의 값이 들어오면 json으로 리스폰스가 들어옵니다.
    fun getCurrentTime(param: String = "currentTime", query: String = "sort", onLoaded: (time: String) -> Unit, onError: (error: ErrorInfo) -> Unit): Disposable {
        //TODO Disposable 을 어떻게 처리 할 것인가? 고민을 해보아야함.
        return getCurrentTimeObservable(param, query).subscribe({ content -> onLoaded(content) },
                { t ->
                    if (t is HttpException) {
                        //NOTE http response 가 200이 아닌 경우 여기로 온다고 합니다.
                        onError(ErrorInfo(t.message(), t.code().toString()))
                    } else {
                        onError(ErrorInfo(t.localizedMessage))
                    }
                }
        )
    }

    // RX api Test 를 위해서 분리하였음
    fun getCurrentTimeObservable(param: String = "currentTime", query: String = "sort", scheduler: Scheduler = AndroidSchedulers.mainThread()): Observable<String> {
        return apis.getCurrentTime(param, query).subscribeOn(Schedulers.io())
                .observeOn(scheduler) // fixme 안드로이드 main thread 로 호출하면 error 가 발생
                .map { body ->
                    when (body.contentType()?.subtype()) {
                        "xml" -> throw Exception(Persister().read(ErrorInfo::class.java, body.string()).toString())
                        "json" -> throw Exception(Gson().fromJson(body.string(), ErrorInfo::class.java).toString())
                        "plain" -> body.string()
                        else -> throw Exception("Other Type Contents : " + body.contentType())
                    }
                }
    }
}