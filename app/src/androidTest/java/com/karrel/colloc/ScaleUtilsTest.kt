package com.karrel.colloc

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.karrel.colloc.utils.ScaleUtils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.util.DisplayMetrics
import android.util.Log


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ScaleUtilsTest {

    @Test
    fun dp2pxText() {

        val appContext = InstrumentationRegistry.getTargetContext()
        Log.i("test", "appContext.resources.displayMetrics.densityDpi/ appContext.resources.displayMetrics.densityDpi" +
                "   ${appContext.resources.displayMetrics.densityDpi.toFloat() } / ${DisplayMetrics.DENSITY_DEFAULT.toFloat()} " +
                "= ${appContext.resources.displayMetrics.densityDpi  / DisplayMetrics.DENSITY_DEFAULT}")
        //2.65 * 3
        val px2 = 2f * appContext.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
        val px3 = 3f * appContext.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

        assertEquals(px2.toInt(), ScaleUtils.dp2px(appContext, 2f))
        assertEquals(px3.toInt(), ScaleUtils.dp2px(appContext, 3f))
    }

}
