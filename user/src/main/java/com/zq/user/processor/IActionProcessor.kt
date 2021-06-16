package com.zq.user.processor

import com.billy.cc.core.component.CC


/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-16 10:55
 **/
interface IActionProcessor {
    fun getActionName(): String

    /**
     * action的处理类
     * @param cc cc
     * @return 是否异步调用 [CC.sendCCResult] . true：异步， false：同步调用
     */
    fun onActionCall(cc: CC?): Boolean

}