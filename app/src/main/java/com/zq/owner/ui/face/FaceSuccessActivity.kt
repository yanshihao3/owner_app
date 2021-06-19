package com.zq.owner.ui.face

import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityFaceSuccessBinding

class FaceSuccessActivity : BaseNoModelActivity<AppActivityFaceSuccessBinding>() {

    override val layoutId = R.layout.app_activity_face_success


    override fun initView() {
        mDataBind.toolbar.title.text = "人脸认证"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }

    }

    override fun initData() {

    }


}