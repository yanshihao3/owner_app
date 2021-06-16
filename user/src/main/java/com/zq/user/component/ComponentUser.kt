package com.zq.user.component

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent
import com.billy.cc.core.component.IMainThread
import com.zq.user.processor.IActionProcessor
import java.util.concurrent.atomic.AtomicBoolean


class ComponentUser : IComponent, IMainThread {

    private val initialized: AtomicBoolean = AtomicBoolean(false)
    private val map: HashMap<String, IActionProcessor> = HashMap(4)

    private fun initProcessors() {}
    private fun add(processor: IActionProcessor) {
        map[processor.getActionName()] = processor
    }

    override fun getName(): String {
        //指定组件的名称
        return "ComponentUser"
    }

    override fun onCall(cc: CC): Boolean {
        if (initialized.compareAndSet(false, true)) {
            synchronized(map) { initProcessors() }
        }
        val actionName = cc.actionName
        val processor = map[actionName]
        if (processor != null) {
            return processor.onActionCall(cc)
        }
        CC.sendCCResult(cc.callId, CCResult.errorUnsupportedActionName())
        return false
    }

    override fun shouldActionRunOnMainThread(actionName: String?, cc: CC?): Boolean {
        return "login" == actionName
    }
}