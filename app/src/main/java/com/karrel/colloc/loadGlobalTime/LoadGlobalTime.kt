package com.karrel.colloc.loadGlobalTime

import com.google.gson.Gson
import com.karrel.colloc.loadGlobalTime.model.ErrorInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
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
            @Path("param")param : String,
            @Query("sort") query : String
    ) : Observable<ResponseBody>
}

private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.addInterceptor(interceptor)
    return builder.build()
}


class LoadGlobalTime {

    companion object {
        val apis =  Retrofit.Builder().run {
            baseUrl("https://global.apis.naver.com/")  //NOTE 일반경우
//            baseUrl("http://apis.navasdfasdfasdfer.com")  //NOTE 타임아웃.
//            baseUrl("http://apis.naver.com")  //NOTE XML
                    .client(createOkHttpClient())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build()
        }
    }

    fun load(param : String = "currentTime",query : String = "sort",onLoaded : (time : String)->Unit,onError : (error : ErrorInfo)->Unit){
        val services= apis.create(NaverGlobalTimeService::class.java)

        //TODO Disposable 을 어떻게 처리 할 것인가? 고민을 해보아야함.
        val disposable = services.loadCurrentTimeRx(param,query)
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
                .subscribe(
                        { content ->
                            onLoaded(content)
                        },
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