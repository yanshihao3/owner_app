package com.zq.owner.ui.service.live

import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityLiveBinding

/**
 * 居住证明界面
 */
class LiveActivity : BaseNoModelActivity<AppActivityLiveBinding>() {


    override val layoutId: Int = R.layout.app_activity_live


    override fun initView() {
        mDataBind.toolbar.title.text = "居住证明"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }

    }

    override fun initData() {

    }
}