package com.karrel.colloc

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by KIM on 2018-07-03.
 */

object RestfulAdpater {
    private val BASE_URL = ""

    fun getRestfulApi(): AirApiService {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .let {
                    return it.create(AirApiService::class.java)
                }
    }
}