package com.zq.owner.ui.community.house

import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityBindHouseBinding

class BindHouseActivity : BaseNoModelActivity<AppActivityBindHouseBinding>() {
    override val layoutId: Int = R.layout.app_activity_bind_house


    override fun initView() {
        mDataBind.toolbar.title.text="绑定房屋"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
    }

    override fun initData() {

    }

}