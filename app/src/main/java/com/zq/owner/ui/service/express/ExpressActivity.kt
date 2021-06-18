package com.zq.owner.ui.service.express

import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityExpressBinding

/**
 * 快递服务界面
 */
class ExpressActivity : BaseNoModelActivity<AppActivityExpressBinding>() {

    override val layoutId: Int = R.layout.app_activity_express

    override fun initView() {
        mDataBind.toolbar.title.text = "快递服务"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
    }

    override fun initData() {

    }
}