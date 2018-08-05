package com.karrel.colloc.api

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit.SECONDS

object CollocRetrofitClient {

    private val okHttpClient: OkHttpClient

    init {
        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, SECONDS)
                .readTimeout(30, SECONDS)
                .writeTimeout(30, SECONDS)
                .connectionPool(ConnectionPool())
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }

    fun <T> create(baseUrl: String, service: Class<T>): T {

        return Retrofit.Builder().apply {
            baseUrl(baseUrl).client(okHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())     // fixme 여기에 body parsing 이 여기로 옮겨져야 합니다. @windwhiser Converter.Factory 찾아보시면 좋을거 같아요
        }.build().create(service)

    }

}