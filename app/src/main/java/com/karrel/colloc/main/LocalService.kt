package com.karrel.colloc.main

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//https://dapi.kakao.com/v2/local/geo/transcoord.json?input_coord=WGS84&output_coord=TM
//https://dapi.kakao.com/v2/local/geo/transcoord.json?x=126.57821604896051&y=33.45613394001848&input_coord=WGS84&output_coord=TM
interface LocalService {
    @Headers("Authorization:KakaoAK 33d26044f8df1f8bdae41a54341852d5")
    @GET("https://dapi.kakao.com/v2/local/geo/transcoord.json?input_coord=WGS84&output_coord=TM")
    fun getTM(@Query("x") long: Double,
              @Query("y") lan: Double
    ): Observable<TranscoordData>
}

data class TranscoordData(
        var meta: Meta,
        var documents: List<Documents>
)

data class Meta(
        var total_count: Int
)

data class Documents(
        var x: Double,
        var y: Double
)