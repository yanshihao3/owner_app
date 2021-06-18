package com.zq.owner.ui.community.report


import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityReportRecordBinding

class ReportRecordActivity : BaseNoModelActivity<AppActivityReportRecordBinding>() {


    override val layoutId: Int = R.layout.app_activity_report_record


    override fun initView() {
        mDataBind.toolbar.title.text = "报事报修"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }

        mDataBind.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int) = RecordFragment.newInstance("type")

        }
        mDataBind.viewPager.isUserInputEnabled = false
        mDataBind.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "全部" -> mDataBind.viewPager.currentItem = 0
                    "处理中" -> mDataBind.viewPager.currentItem = 1
                    "待评价" -> mDataBind.viewPager.currentItem = 2
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


}