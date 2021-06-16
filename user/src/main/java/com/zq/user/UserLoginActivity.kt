package com.zq.user


import android.content.Intent
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.zq.base.activity.BaseActivity
import com.zq.user.databinding.UserActivityLoginBinding
import com.zq.user.viewmodel.LoginViewModel

class UserLoginActivity : BaseActivity<LoginViewModel, UserActivityLoginBinding>() {

    override val layoutId: Int = R.layout.user_activity_login
    lateinit var callId: String


    override fun initView() {
        callId = CCUtil.getNavigateCallId(this)
        mDataBind.btn.setOnClickListener {
            startActivityForResult(Intent(mActivityContext, UserCodeActivity::class.java), 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == 0) {//登录成功
            sendResult()
            finish()
        } else {
            sendLoginResult()
            finish()
        }
    }

    override fun initData() {

    }

    private var resultSent = false


    override fun onDestroy() {
        super.onDestroy()
        //为确保一定会调用CC.sendCCResult，在onDestroy中再次确认是否已返回登录结果
        // sendLoginResult()
    }

    private fun sendLoginResult() {
        if (resultSent) {
            return
        }
        resultSent = true
        val result: CCResult = CCResult.error("登录失败")
        CC.sendCCResult(callId, result)
    }

    private fun sendResult() {
        if (resultSent) {
            return
        }
        resultSent = true
        val result: CCResult = CCResult.success("data", "success")
        CC.sendCCResult(callId, result)
    }

}