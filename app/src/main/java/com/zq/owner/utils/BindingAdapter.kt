package com.zq.owner.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-23 17:39
 **/
object BindingAdapter {


    @BindingAdapter("bindImage")
    @JvmStatic
    fun bindTextColor(imageView: ImageView, @DrawableRes resId: Int) {
        imageView.setImageResource(resId)
    }

}