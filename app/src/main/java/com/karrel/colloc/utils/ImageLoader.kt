package com.karrel.colloc.utils

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object ImageLoader {

    fun loadImage(context: Context, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadImage(GlideApp.with(context), targetView, imageUrl)
    }

    fun loadImage(activity: Activity, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadImage(GlideApp.with(activity), targetView, imageUrl)
    }

    fun loadImage(fragment: Fragment, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadImage(GlideApp.with(fragment), targetView, imageUrl)
    }


    fun loadCircleImage(context: Context, targetView: ImageView, imageUrl: String) {
        loadCircleImage(GlideApp.with(context), targetView, imageUrl)
    }

    fun loadCircleImage(activity: Activity, targetView: ImageView, imageUrl: String) {
        loadCircleImage(GlideApp.with(activity), targetView, imageUrl)
    }

    fun loadCircleImage(fragment: Fragment, targetView: ImageView, imageUrl: String) {
        loadCircleImage(GlideApp.with(fragment), targetView, imageUrl)
    }


    private fun loadImage(glide: GlideRequests, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        val glideRequest = glide.load(imageUrl)
        if (isNoCache) {
            glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
        }
        glideRequest.into(targetView)
    }

    private fun loadCircleImage(glide: GlideRequests, targetView: ImageView, imageUrl: String) {
        glide.load(imageUrl).apply(RequestOptions.circleCropTransform()).into(targetView)
    }
}