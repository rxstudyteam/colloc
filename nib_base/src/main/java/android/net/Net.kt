package android.net

import android.app.Activity
import android.content.Context
//import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

object Net {
    private lateinit var okHttpClient: OkHttpClient
    fun CREATE(context: Context) {
        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, SECONDS)
                .readTimeout(60, SECONDS)
                .writeTimeout(30, SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
//                .addInterceptor(ChuckInterceptor(context))
                .build()


    }

    fun <T : Activity> create(baseUrl: String , service: Class<T>): T {
        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(okHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(service)
    }
}