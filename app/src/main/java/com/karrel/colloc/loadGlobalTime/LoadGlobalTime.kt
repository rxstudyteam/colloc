package com.karrel.colloc.loadGlobalTime

import com.google.gson.Gson
import com.karrel.colloc.loadGlobalTime.model.ErrorInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.core.Persister
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NaverGlobalTimeService{
    @GET("{param}")
    fun loadCurrentTimeRx(
            @Path("param")param : String,  // @Path 를 사용하면 어노테이션에 지정된 영역에 삽입 할수있습니다.
            @Query("sort") query : String  // @Query 를 사용하면 주어진 주소값 이후 ?value=parameter 형태로 쿼리를 추가 할 수 있습니다.
    ) : Observable<ResponseBody>
}

private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.addInterceptor(interceptor)
    return builder.build()
}

object LoadGlobalTime {

    val apis =  Retrofit.Builder().run {
        baseUrl("https://global.apis.naver.com/")
        .client(createOkHttpClient())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        build()
    }

    //NOTE sort 는 쿼리 테스트를 위하여 추가 한 것이라 리스폰스에 영항이 없음.
    //NOTE param 에 currentTime 이외의 값이 들어오면 json으로 리스폰스가 들어옵니다.
    fun load(param : String = "currentTime",query : String = "sort",onLoaded : (time : String)->Unit,onError : (error : ErrorInfo)->Unit) : Disposable{
        val services= apis.create(NaverGlobalTimeService::class.java)

        //TODO Disposable 을 어떻게 처리 할 것인가? 고민을 해보아야함.
        return services.loadCurrentTimeRx(param,query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {body ->
                    when(body.contentType()?.subtype()){
                        "xml" ->throw Exception(Persister().read(ErrorInfo::class.java, body.string()).toString())
                        "json" -> throw Exception(Gson().fromJson(body.string(), ErrorInfo::class.java).toString())
                        "plain" -> body.string()
                        else ->throw Exception("Other Type Contents : "+body.contentType())
                    }
                }
                .subscribe({ content ->onLoaded(content)},
                        {
                            t->
                            if (t is HttpException) {
                                //NOTE http response 가 200이 아닌 경우 여기로 온다고 합니다.
                                onError(ErrorInfo(t.message(),t.code().toString()))
                            }else {
                                onError(ErrorInfo(t.localizedMessage))
                            }
                        }
                )
    }
}