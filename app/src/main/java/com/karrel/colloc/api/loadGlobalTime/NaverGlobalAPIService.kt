package com.karrel.colloc.api.loadGlobalTime

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NaverGlobalAPIService {

    @GET("{param}")
    fun getCurrentTime(
            @Path("param") param: String,  // @Path 를 사용하면 어노테이션에 지정된 영역에 삽입 할수있습니다.
            @Query("sort") query: String  // @Query 를 사용하면 주어진 주소값 이후 ?value=parameter 형태로 쿼리를 추가 할 수 있습니다.
    ): Observable<ResponseBody>
}