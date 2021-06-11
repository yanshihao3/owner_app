package com.zq.owner.ui.notice

import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentNoticeBinding
import com.zq.owner.ui.notice.viewmodel.NoticeViewModel
import q.rorbin.badgeview.QBadgeView


class NoticeFragment : BaseLazyFragment<NoticeViewModel, AppFragmentNoticeBinding>() {


    override val layoutId: Int
        get() = R.layout.app_fragment_notice

    override fun initView() {
        showBadgeView(1, 99)
        mDataBind!!.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int) = NewsFragment()

        }
        mDataBind!!.viewPager.isUserInputEnabled = false
        mDataBind!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "全部" -> mDataBind!!.viewPager.currentItem = 0
                    "已读" -> mDataBind!!.viewPager.currentItem = 2
                    "未读" -> mDataBind!!.viewPager.currentItem = 1
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

    /**
     * BottomNavigationView显示角标
     *
     * @param viewIndex tab索引
     * @param showNumber 显示的数字，小于等于0是将不显示
     */
    private fun showBadgeView(viewIndex: Int, showNumber: Int) {
        val tabItem =
            (mDataBind!!.tabLayout.getChildAt(0) as ViewGroup).getChildAt(viewIndex) as TabLayout.TabView
        QBadgeView(mContext).bindTarget(tabItem)
            .setBadgeGravity(BadgeDrawable.TOP_END)
            .badgeText = "99+"
    }

    override fun onFragmentFirstVisible() {

    }
}