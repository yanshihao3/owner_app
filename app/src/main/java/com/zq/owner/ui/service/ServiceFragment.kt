package com.zq.owner.ui.service

import androidx.recyclerview.widget.GridLayoutManager
import com.zq.base.decoration.GridSpaceItemDecoration
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentServiceBinding
import com.zq.owner.ui.service.adapter.MenuAdapter
import com.zq.owner.ui.service.entity.MenuItem
import com.zq.owner.ui.service.viewmodel.ServiceViewModel

/**
 * @program: owner_app
 *
 * @description:服务节目
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-08 17:06
 **/
class ServiceFragment : BaseLazyFragment<ServiceViewModel, AppFragmentServiceBinding>() {
    override val layoutId: Int
        get() = R.layout.app_fragment_service
    private val title1 = arrayOf<String>(
        "身份卡", "旅行条", "居住证明", "我的房屋", "住户管理", "车位缴费",
        "访客邀请", "物业缴费", "报事报修", "投诉建议", "专属管家", "投诉热线", "服务电话", "问卷调查"
    )
    private val title2 = arrayOf<String>(
        "社区优先", "社区圈子", "快递查存", "云缴费", "话费充值", "油卡充值"
    )


    private val dataList1 = mutableListOf<MenuItem>()

    private val dataList2 = mutableListOf<MenuItem>()

    private val layoutManager1 by lazy { GridLayoutManager(mContext, 5) }
    private val layoutManager2 by lazy { GridLayoutManager(mContext, 5) }

    private val adapter1 by lazy { MenuAdapter() }
    private val adapter2 by lazy { MenuAdapter() }

    override fun onFragmentFirstVisible() {
        for (title in title1) {
            dataList1.add(MenuItem("", title))
        }

        for (title in title2) {
            dataList2.add(MenuItem("", title))
        }
        adapter1.data = dataList1
        adapter2.data = dataList2

    }

    override fun initView() {
        mDataBind!!.recyclerView.layoutManager = layoutManager1
        mDataBind!!.recyclerView.adapter = adapter1
        mDataBind!!.recyclerView.addItemDecoration(GridSpaceItemDecoration(24))
        mDataBind!!.recyclerView2.layoutManager = layoutManager2
        mDataBind!!.recyclerView2.adapter = adapter2
        mDataBind!!.recyclerView2.addItemDecoration(GridSpaceItemDecoration(24))

    }

    override fun initData() {
    }


}