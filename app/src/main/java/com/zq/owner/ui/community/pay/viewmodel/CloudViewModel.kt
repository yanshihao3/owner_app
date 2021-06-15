package com.zq.owner.ui.community.pay.viewmodel

import android.view.View
import com.zq.base.viewmodel.BaseViewModel

/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-15 17:45
 **/
class CloudViewModel : BaseViewModel() {
    override fun load() {

    }

    /**
     * 绑定的点击事件
     */
    fun click(view: View) {
        defUI.toastEvent.postValue("当前功能正在开发中。。。")
    }
}