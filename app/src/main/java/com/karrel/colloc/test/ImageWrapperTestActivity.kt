package com.karrel.colloc.test

import android.os.Bundle
import com.karrel.colloc.R
import com.karrel.colloc.base.BaseActivity
import com.karrel.colloc.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_test_image_loader.*

class ImageWrapperTestActivity :BaseActivity(){

    override val requestPermissionList: List<String> = emptyList()
    override val layoutResID: Int = R.layout.activity_test_image_loader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do something
    }


    override val initView: () -> Unit = {
        ImageLoader.loadImage(this, image_test_1, "https://s-i.huffpost.com/gen/5024096/thumbs/o-IMAGES-570.jpg?7")
        ImageLoader.loadCircleImage(this, image_test_2, "https://s-i.huffpost.com/gen/5024096/thumbs/o-IMAGES-570.jpg?7")
        ImageLoader.loadImage(this, image_test_3, "https://pbs.twimg.com/profile_images/879901726739808256/ry_UkEdB_400x400.jpg", true)
        ImageLoader.loadCircleImage(this, image_test_4, "http://walartmuseum.or.kr/2015/wp-content/uploads/2014/09/%ED%99%94%EB%B3%91943x1115_v21.jpg", true)
    }

}