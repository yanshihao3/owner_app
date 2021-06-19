package com.zq.owner.ui.service.circle

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityCircleBinding

/**
 * 社区圈子界面
 */
class CircleActivity : BaseNoModelActivity<AppActivityCircleBinding>() {

    override val layoutId: Int = R.layout.app_activity_circle
    private val titles = arrayListOf("全部", "分类一", "分类二", "分类三", "分类四")

    override fun initView() {
        mDataBind.toolbar.title.text = "社区圈子"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }

        mDataBind.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 5

            override fun createFragment(position: Int) = CircleFragment()

        }
        mDataBind.viewPager.isUserInputEnabled = false
        val tabLayoutMediator =
            TabLayoutMediator(mDataBind.tabLayout, mDataBind.viewPager) { tab, position ->
                tab.text = titles[position]

            }
        tabLayoutMediator.attach()
    }


    override fun initData() {

    }
}