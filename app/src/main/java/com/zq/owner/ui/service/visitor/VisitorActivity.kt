package com.zq.owner.ui.service.visitor

import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityVisitorBinding

class VisitorActivity : BaseNoModelActivity<AppActivityVisitorBinding>() {

    override val layoutId: Int = R.layout.app_activity_visitor


    override fun initView() {
        mDataBind.toolbar.title.text = "访客邀请"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
    }

    override fun initData() {

    }
}