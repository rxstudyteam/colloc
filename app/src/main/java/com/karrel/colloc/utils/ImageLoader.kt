package com.karrel.colloc.utils

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object ImageLoader {

    fun loadImage(context: Context, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadImage(GlideApp.with(context), targetView, imageUrl, isNoCache)
    }

    fun loadImage(activity: Activity, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadImage(GlideApp.with(activity), targetView, imageUrl, isNoCache)
    }

    fun loadImage(fragment: Fragment, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadImage(GlideApp.with(fragment), targetView, imageUrl, isNoCache)
    }

    fun loadCircleImage(context: Context, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadCircleImage(GlideApp.with(context), targetView, imageUrl, isNoCache)
    }

    fun loadCircleImage(activity: Activity, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadCircleImage(GlideApp.with(activity), targetView, imageUrl, isNoCache)
    }

    fun loadCircleImage(fragment: Fragment, targetView: ImageView, imageUrl: String, isNoCache: Boolean = false) {
        loadCircleImage(GlideApp.with(fragment), targetView, imageUrl, isNoCache)
    }

    private fun loadImage(glide: GlideRequests, targetView: ImageView, imageUrl: String, isNoCache: Boolean) {
        val glideRequest = glide.load(imageUrl)
        if (isNoCache) {
            glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
        }
        glideRequest.into(targetView)
    }

    private fun loadCircleImage(glide: GlideRequests, targetView: ImageView, imageUrl: String, isNoCache: Boolean) {
        val glideRequest = glide.load(imageUrl)

        if (isNoCache) {
            glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
        }
        glideRequest.apply(RequestOptions.circleCropTransform()).into(targetView)
    }


    /*
   // ScaleType 을 적용하면 blur 가 동작하지 않아, imageView 에 직접 scaleType  을 적용해야한다.
   fun loadBlurImage(context: Context, targetView: ImageView, imageUrl: String) {
       loadBlurImage(GlideApp.with(context), targetView, imageUrl)
   }

   fun loadBlurImage(activity: Activity, targetView: ImageView, imageUrl: String) {
       loadBlurImage(GlideApp.with(activity), targetView, imageUrl)
   }

   fun loadBlurImage(fragment: Fragment, targetView: ImageView, imageUrl: String) {
       loadBlurImage(GlideApp.with(fragment), targetView, imageUrl)
   }

   private fun loadBlurImage(glide: GlideRequests, targetView: ImageView, imageUrl: String){
       glide.load(imageUrl).apply(RequestOptions.bitmapTransform(BlurTransformation())).into(targetView)
   }
   */

}