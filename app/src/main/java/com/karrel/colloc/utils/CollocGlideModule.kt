package com.karrel.colloc.utils

import android.content.Context
import android.util.Log
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.request.RequestOptions


//https://bumptech.github.io/glide/doc/configuration.html


@GlideModule
class CollocGlideModule : AppGlideModule() {


    override fun applyOptions(context: Context, builder: GlideBuilder) {


        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
        builder.setLogLevel(Log.VERBOSE);


        //cache https://bumptech.github.io/glide/doc/caching.html#clearing-the-disk-cache

        val calculator = MemorySizeCalculator.Builder(context).setMemoryCacheScreens(2f).build() //screens 2 가 어느정도일까?
        builder.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))

        val diskCacheSizeBytes = 1024 * 1024 * 100 // 100 MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))

    }
}

