package com.zq.owner.ui.community.pay

import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityPayBinding
import q.rorbin.badgeview.QBadgeView

class PayActivity : BaseNoModelActivity<AppActivityPayBinding>() {


    override val layoutId: Int
        get() = R.layout.app_activity_pay

    override fun initView() {
        mDataBind.toolbar.title.text = "物业缴费"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
        showBadgeView(0, "2")
        mDataBind.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2

            override fun createFragment(position: Int) = PayInfoFragment()

        }
        mDataBind.viewPager.isUserInputEnabled = false
        mDataBind.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "缴费" -> mDataBind.viewPager.currentItem = 0
                    "预缴" -> mDataBind.viewPager.currentItem = 1
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun initData() {

    }

    private fun showBadgeView(viewIndex: Int, showText: String) {
        val tabItem =
            (mDataBind!!.tabLayout.getChildAt(0) as ViewGroup).getChildAt(viewIndex) as TabLayout.TabView
        QBadgeView(mActivityContext).bindTarget(tabItem)
            .setBadgeGravity(BadgeDrawable.TOP_END)
            .badgeText = showText
    }
}