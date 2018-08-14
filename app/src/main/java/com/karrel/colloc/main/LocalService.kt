package com.karrel.colloc.main

import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


fun getLocalService(): LocalService {
    return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dapi.kakao.com/v2/local/geo/")
            .build()
            .create(LocalService::class.java)
}

//https://dapi.kakao.com/v2/local/geo/transcoord.json?input_coord=WGS84&output_coord=TM
//https://dapi.kakao.com/v2/local/geo/transcoord.json?x=126.57821604896051&y=33.45613394001848&input_coord=WGS84&output_coord=TM
interface LocalService {
    @Headers("Authorization:KakaoAK 33d26044f8df1f8bdae41a54341852d5")
    @GET("transcoord.json?input_coord=WGS84&output_coord=TM")
    fun getTM(@Query("x") long: Double,
              @Query("y") lan: Double
    ): Observable<TranscoordData>
}

class TranscoordData {
    var meta: Meta? = null
    var documents: Array<Documents>? = null

    class Documents {
        var x: Double = 0.0
        var y: Double = 0.0
    }

    class Meta {
        var total_count: Int = 0
    }

}

