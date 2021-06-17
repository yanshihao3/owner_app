package com.zq.owner.ui.community.house


import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.activity.BaseNoModelActivity
import com.zq.base.decoration.SpacesItemDecoration
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityHouseBinding

/**
 * 我的房屋界面
 */
class HouseActivity : BaseNoModelActivity<AppActivityHouseBinding>() {
    override val layoutId: Int = R.layout.app_activity_house
    private val adapter by lazy {
        PersonAdapter()
    }
    private val listData = mutableListOf(
        PersonModel("", "", "", ""),
        PersonModel("", "", "", "")
    )

    override fun initView() {
        mDataBind.toolbar.title.text="我的房屋"
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
