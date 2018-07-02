package com.karrel.colloc.utils

import android.content.Context
import android.util.TypedValue


object ScaleUtils {

    fun dp2px(context: Context, dp: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()
    }
}