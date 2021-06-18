package com.zq.owner.ui.service.household

import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.activity.BaseNoModelActivity
import com.zq.base.decoration.SpacesItemDecoration
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityHouseholdBinding

/**
 * 住户管理
 */
class HouseholdActivity : BaseNoModelActivity<AppActivityHouseholdBinding>() {


    override val layoutId: Int = R.layout.app_activity_household

    private val adapter by lazy {
        HouseholdAdapter()
    }
    private val listData = mutableListOf(
        PeopleModel(),
        PeopleModel()
    )

    override fun initView() {
        mDataBind.toolbar.title.text = "住户管理"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
        mDataBind.recyclerView.layoutManager = LinearLayoutManager(mActivityContext)
        mDataBind.recyclerView.adapter = adapter
        val spacesItemDecoration = SpacesItemDecoration(mActivityContext)
        spacesItemDecoration.setParam(resources.getColor(R.color.line_color), 1f)
        mDataBind.recyclerView.addItemDecoration(spacesItemDecoration)
    }

    override fun initData() {
        adapter.data = listData
        adapter.notifyDataSetChanged()
    }
}