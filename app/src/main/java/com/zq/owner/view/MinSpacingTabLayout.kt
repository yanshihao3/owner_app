package com.zq.owner.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

/**
 * @program: owner_app
 *
 * @description:修改下Tab的最小间距了
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-17 19:25
 **/
class MinSpacingTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    TabLayout(context, attrs, defStyleAttr) {

    init {
        initMinWidth()
    }

    private fun initMinWidth() {
        try {
            val field = TabLayout::class.java.getDeclaredField("scrollableTabMinWidth")
            field.isAccessible = true
            // 设定最小的间距
            field.set(this, 10)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
