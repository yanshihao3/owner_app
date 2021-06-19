package com.zq.owner.ui.service.mail


import androidx.recyclerview.widget.GridLayoutManager
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentMallBinding

class MallFragment : BaseLazyFragment<ViewModel, AppFragmentMallBinding>() {

    override val layoutId: Int = R.layout.app_fragment_mall

    private val adapter by lazy {
        GoodsAdapter()
    }

    private val dataList = arrayListOf(
        GoodsModel(),
        GoodsModel(),
        GoodsModel(),
        GoodsModel(),
        GoodsModel(),
        GoodsModel(),
        GoodsModel()
    )

    override fun initView() {
        mDataBind.recyclerView.layoutManager = GridLayoutManager(context, 2)
        mDataBind.recyclerView.adapter = adapter
    }

    override fun initData() {
        adapter.data = dataList
        adapter.notifyDataSetChanged()
    }

    override fun onFragmentFirstVisible() {

    }


}