package com.zq.owner.application

import android.content.Context
import android.widget.ImageView
import coil.load
import com.lqr.emoji.LQREmotionKit
import com.zq.base.BaseApplication
import com.zq.network.ServerApi
import com.zq.owner.BuildConfig

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-03 17:40
 **/
class NewApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        setDebug(BuildConfig.DEBUG)
        ServerApi.getInstance().init(this)
        //初始化表情控件
        LQREmotionKit.init(
            this
        ) { _: Context?, path: String?, imageView: ImageView? ->
            imageView?.load(path)
        }

    }
}