package com.karrel.colloc

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by KIM on 2018-07-03.
 */

interface AirApiService {
    @GET("/Sample")
    fun getSampleAll(@Query("search") search: String): Observable<AirModel.Result>
}