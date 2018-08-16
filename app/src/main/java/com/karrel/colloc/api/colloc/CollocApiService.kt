package com.karrel.colloc.api.colloc

import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

//http://karrel84.cafe24.com/colloc/airdata?tmX=244148.546388&tmY=412423.75772
interface CollocApiService {
    @GET("colloc/airdata")
    fun getAirData(
            @Query("tmX") tmY: String,
            @Query("tmY") tmX: String
    ): Observable<AirData>
}