package com.zq.owner.ui.face

import android.content.Intent
import android.net.Uri
import coil.load
import com.zq.base.activity.BaseNoModelActivity
import com.zq.base.utils.SharedPreferencesUtils
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityFaceResultBinding

/**
 * 已经认证的界面
 */
class FaceResultActivity : BaseNoModelActivity<AppActivityFaceResultBinding>() {


    override val layoutId: Int = R.layout.app_activity_face_result


    override fun initView() {
        mDataBind.toolbar.title.text = "人脸认证"
        mDataBind.btn.setOnClickListener {
            startActivity(Intent(mActivityContext, FaceActivity::class.java))
            finish()
        }
        val faceUri = SharedPreferencesUtils.init(this).getString("face")
        mDataBind.faceView.load(Uri.parse(faceUri))
    }

    override fun initData() {

    }
}