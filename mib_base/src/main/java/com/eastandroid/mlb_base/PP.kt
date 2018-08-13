package com.eastandroid.mlb_base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager

/**
 * <pre>
 * 아래와 같이 사용하세요
 * 변수 타입을 혼용하여 사용하면 죽음!
 * PP.sample.is();
 * PP.sample.set(true);
 *
 * PP.sample.getInt();
 * PP.sample.set(1);
 *
 * PP.sample.getLong();
 * PP.sample.set(1L);
 *
 * PP.sample.get();
 * PP.sample.getString();
 * v.sample.set(&quot;text&quot;);
 *
</pre> *
 */


enum class PP {
    LOCATIONS;

    fun getBoolean(defValues: Boolean = DEFVALUE_BOOLEAN): Boolean {
        return PREFERENCES.getBoolean(name, defValues)
    }

    fun `is`(defValues: Boolean = DEFVALUE_BOOLEAN): Boolean {
        return PREFERENCES.getBoolean(name, defValues)
    }

    fun getInt(defValues: Int = DEFVALUE_INT): Int {
        return PREFERENCES.getInt(name, defValues)
    }

    fun getLong(defValues: Long = DEFVALUE_LONG): Long {
        return PREFERENCES.getLong(name, defValues)
    }

    fun getFloat(defValues: Float = DEFVALUE_FLOAT): Float {
        return PREFERENCES.getFloat(name, defValues)
    }

    fun getString(defValues: String = DEFVALUE_STRING): String {
        return PREFERENCES.getString(name, defValues)
    }

    fun `get`(defValues: String = DEFVALUE_STRING): String {
        return PREFERENCES.getString(name, defValues)
    }

    fun getStringSet(defValues: Set<String> = setOf()): Set<String> {
        return PREFERENCES.getStringSet(name, defValues)
    }


    fun set(v: Boolean) {
        PREFERENCES.edit().putBoolean(name, v).apply()
    }

    fun toggle() {
        PREFERENCES.edit().putBoolean(name, !PREFERENCES.getBoolean(name, DEFVALUE_BOOLEAN)).apply()
    }

    fun set(v: Int) {
        PREFERENCES.edit().putInt(name, v).apply()
    }

    fun set(v: Long) {
        PREFERENCES.edit().putLong(name, v).apply()
    }

    fun set(v: Float) {
        PREFERENCES.edit().putFloat(name, v).apply()
    }

    fun set(v: String) {
        PREFERENCES.edit().putString(name, v).apply()
    }

    fun set(v: Set<String>) {
        PREFERENCES.edit().putStringSet(name, v).apply()
    }


    fun contain(): Boolean {
        return PREFERENCES.contains(name)
    }

    fun remove(): Boolean {
        return PREFERENCES.edit().remove(name).commit()
    }

    companion object {
        private val DEFVALUE_STRING = ""
        private val DEFVALUE_FLOAT = -1f
        private val DEFVALUE_INT = -1
        private val DEFVALUE_LONG = -1L
        val DEFVALUE_BOOLEAN = false

        private lateinit var PREFERENCES: SharedPreferences

        fun CREATE(context: Context) {
            PREFERENCES = PreferenceManager.getDefaultSharedPreferences(context)
            UPDATE()
        }

        //초기값지정
        fun UPDATE() {}

        //실재값에 변화가 있을때만 event가 날라온다
        fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
            PREFERENCES.registerOnSharedPreferenceChangeListener(listener)
        }

        fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
            PREFERENCES.unregisterOnSharedPreferenceChangeListener(listener)
        }

        fun clear(): Boolean {
            return PREFERENCES.edit().clear().commit()
        }
    }
}




