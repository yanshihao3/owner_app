package com.zq.owner.ui.service.express

import android.os.Bundle
import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityExpressBinding

/**
 * 快递服务界面
 */
class ExpressActivity : BaseNoModelActivity<AppActivityExpressBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_express)
    }

    override val layoutId: Int = R.layout.app_activity_express


    override fun initView() {

    }

    override fun initData() {

    }
}