package android.net

//import com.readystatesoftware.chuck.ChuckInterceptor
import android.content.Context
import android.log.Log
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Net {
    const val EUCKR = "euc-kr"
    const val UTF8 = "UTF-8"
    const val COOKIE = "Cookie"
    //    public static final String SET_COOKIE = "set-cookie";
    const val SET_COOKIE = "Set-Cookie"

    const val SESSIONID = "PSHSESSIONID"
    const val USER_AGENT = "User-Agent"

    var LOG = true

    var _OUT_1 = true
    var _OUT_2 = true
    var _OUT_3 = true
    var _OUT_H = true

    var _IN_1 = true
    var _IN_2 = true
    var _IN_3 = true
    var _IN_H = true

    private lateinit var okHttpClient: OkHttpClient

    fun CREATE(context: Context) {
        okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor())
            addInterceptor(Logger())
            addInterceptor(ChuckInterceptor(context))
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