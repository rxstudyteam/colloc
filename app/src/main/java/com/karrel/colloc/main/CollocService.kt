package com.karrel.colloc.main

import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


fun getCollocService(): CollocService {
    return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://karrel84.cafe24.com/")
            .build()
            .create(CollocService::class.java)
}

interface CollocService {
    @GET("colloc/airdata")
    fun getAirData(@Query("tmX") tmX: Double, @Query("tmY") tmY: Double): Observable<AirData>
}
