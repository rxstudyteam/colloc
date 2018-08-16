package android.net

import android.app.Activity
import android.content.Context
import android.log.Log
import com.readystatesoftware.chuck.ChuckInterceptor
//import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.util.concurrent.TimeUnit.SECONDS

object Net {
    const val EUCKR = "euc-kr"
    const val UTF8 = "UTF-8"
    const val COOKIE = "Cookie"
    //    public static final String SET_COOKIE = "set-cookie";
    const val SET_COOKIE = "Set-Cookie"

    const val SESSIONID = "PSHSESSIONID"
    const val USER_AGENT = "User-Agent"

    var LOG = false

    var _OUT_1 = false
    var _OUT_2 = false
    var _OUT_3 = false
    var _OUT_H = false

    var _IN_1 = false
    var _IN_2 = false
    var _IN_3 = false
    var _IN_H = false

    private lateinit var okHttpClient: OkHttpClient

    fun CREATE(context: Context) {
        okHttpClient = OkHttpClient.Builder().apply {
            //            addInterceptor(HttpLoggingInterceptor())
            addInterceptor(Logger())
            addInterceptor(ChuckInterceptor(context))
            addNetworkInterceptor {
                val req = it.request()
                Log.e("~~>" + req.url())
                val res = it.proceed(req)
                Log.i(res.body().toString())
                res
            }
        }.build()
    }


    fun <T> create(service: Class<T>): T {
        return Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl("https://my/")
//            baseUrl("https://dapi.kakao.com")
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(service)
    }
}