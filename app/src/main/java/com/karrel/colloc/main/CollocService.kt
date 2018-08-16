package com.karrel.colloc.main

import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CollocService {
    @GET("http://karrel84.cafe24.com/colloc/airdata")
    fun getAirData(@Query("tmX") tmX: Double, @Query("tmY") tmY: Double): Observable<AirData>
}
