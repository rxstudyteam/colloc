package com.eastandroid.nlb_base

import org.junit.Test

class ExampleUnitTest {
    @Test
    fun HEX() {
        println(Integer.toHexString(7))


        println(byteArrayOf(0, 1, 2, 3, 4, 5, 6).toHex())
    }

    private val HEX_CHARS = "0123456789ABCDEF".toCharArray()
    fun ByteArray.toHex(): String {
        val result = StringBuilder()
        forEach {
            val octet = it.toInt()
            val firstIndex = (octet and 0xF0).ushr(4)
            val secondIndex = octet and 0x0F
            result.append(HEX_CHARS[firstIndex])
            result.append(HEX_CHARS[secondIndex])
        }
        return result.toString()
    }
}