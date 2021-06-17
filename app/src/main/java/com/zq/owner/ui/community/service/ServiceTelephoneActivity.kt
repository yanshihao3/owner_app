package com.zq.owner.ui.community.service


import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.activity.BaseActivity
import com.zq.base.decoration.SpacesItemDecoration
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityServiceTelephoneBinding
import com.zq.owner.ui.community.service.adapter.PhoneAdapter
import com.zq.owner.ui.community.service.model.PhoneInfo
import com.zq.owner.ui.community.service.viewmodel.PhoneViewModel

class ServiceTelephoneActivity :
    BaseActivity<PhoneViewModel, AppActivityServiceTelephoneBinding>() {


    override val layoutId: Int = R.layout.app_activity_service_telephone

    private val phoneAdapter by lazy {
        PhoneAdapter()
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(mActivityContext)
    }

    private val listData = mutableListOf<PhoneInfo>()

    override fun initView() {
        mDataBind.toolbar.title.text = "服务电话"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
        mDataBind.recyclerView.layoutManager = linearLayoutManager
        mDataBind.recyclerView.adapter = phoneAdapter
        mDataBind.recyclerView.addItemDecoration(
            SpacesItemDecoration(mActivityContext).setParam(
                resources.getColor(R.color.line_color),
                1f,
                16f,
                0f
            )
        )
    }

    override fun initData() {
        listData.add(PhoneInfo("客服中心1号", "1231231231"))
        listData.add(PhoneInfo("客服中心2号", "12312467"))
        listData.add(PhoneInfo("客服中心3号", "123131"))
        phoneAdapter.data = listData
        phoneAdapter.notifyDataSetChanged()
    }
}