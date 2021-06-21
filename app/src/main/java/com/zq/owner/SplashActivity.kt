package com.zq.owner

import android.content.Intent
import com.hjq.permissions.Permission
import com.zq.base.activity.BasePermissionActivity
import com.zq.base.permission.RequestPermissionListener
import com.zq.owner.databinding.AppActivitySplashBinding
import com.zq.owner.viewmodel.SplashViewModel

class SplashActivity : BasePermissionActivity<SplashViewModel, AppActivitySplashBinding>(),
    RequestPermissionListener {


    override val layoutId: Int = R.layout.app_activity_splash

    override fun initView() {

    }

    override fun onResume() {
        super.onResume()
        checkPermission(
            this,
            Permission.CALL_PHONE,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.CAMERA
        )
    }


    override fun initData() {
        mDataBind.btn.setOnClickListener {
            toMain()
        }
    }

    override fun agreeAllPermission() {

    }

    override fun agreePermission() {

    }

    override fun onDenied() {

    }

    private fun toMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}