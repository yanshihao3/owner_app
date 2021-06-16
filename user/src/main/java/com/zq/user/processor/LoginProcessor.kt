package com.zq.user.processor

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCUtil
import com.zq.user.UserLoginActivity


/**
 * @program: owner_app
 *
 * @description:登录
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-16 10:56
 **/
class LoginProcessor : IActionProcessor {
    override fun getActionName(): String {
        return "login"
    }

    override fun onActionCall(cc: CC?): Boolean {
        CCUtil.navigateTo(cc, UserLoginActivity::class.java)
        //不立即调用CC.sendCCResult,返回true
        return true
    }
}