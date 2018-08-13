package com.karrel.colloc

import com.karrel.colloc.api.loadGlobalTime.NaverGlobalAPIProvider
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

class CollocAPITest {

    @DisplayName("여기에 테스트할 API 명을 적어주세요 ")
    @Test
    fun kotlinTest() {
        val expected = 3;
        val actual = 1 + 2;

        assertEquals(expected, actual);
    }

    @DisplayName("LoadGlobalTime load api ")
    @Test
    fun testNaverGlobalCurrentTime() {
        val currentTime = System.currentTimeMillis()
        val source = NaverGlobalAPIProvider.getCurrentTimeObservable(scheduler = Schedulers.newThread())
        source.doOnNext {
            System.out.println(it)
        }
                .test()
                .awaitDone(3, TimeUnit.SECONDS)
//                .assertResult(currentTime.toString())
                .assertComplete()
    }
}