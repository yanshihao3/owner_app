package com.zq.owner.ui.service.circle

import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentCircleBinding


class CircleFragment : BaseLazyFragment<CircleViewModel, AppFragmentCircleBinding>() {


    override val layoutId: Int = R.layout.app_fragment_circle
    private val adapter by lazy {
        CircleAdapter()
    }

    private val dataList = arrayListOf(
        CircleModel(),
        CircleModel(),
        CircleModel()

    )

    override fun initView() {
        mDataBind.recyclerView.layoutManager = LinearLayoutManager(context)
        mDataBind.recyclerView.adapter = adapter

    }

    override fun initData() {
        adapter.data = dataList
        adapter.notifyDataSetChanged()
    }

    override fun onFragmentFirstVisible() {

    }
}


