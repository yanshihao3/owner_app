package com.zq.owner.ui.community.pay

import com.zq.base.activity.BaseActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityCloudPayBinding
import com.zq.owner.ui.community.pay.viewmodel.CloudViewModel

class CloudPayActivity : BaseActivity<CloudViewModel, AppActivityCloudPayBinding>() {


    override val layoutId: Int = R.layout.app_activity_cloud_pay


    override fun initView() {
        mDataBind.itemCableTv.setOnClickListener {
            mViewModel.click(it)
        }

    }

    override fun initData() {

    }
}