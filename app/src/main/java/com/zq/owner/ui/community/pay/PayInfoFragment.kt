package com.zq.owner.ui.community.pay

import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentPayInfoBinding
import com.zq.owner.ui.community.pay.viewmodel.PayInfoViewModel


/**
 * 物业缴费的 fragment
 */
class PayInfoFragment : BaseLazyFragment<PayInfoViewModel, AppFragmentPayInfoBinding>() {

    override val layoutId: Int = R.layout.app_fragment_pay_info

    private val listData = arrayListOf(PayInfoModel(), PayInfoModel())

    private val adapter by lazy {
        PayInfoAdapter()
    }

    override fun initView() {
        mDataBind.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mDataBind.recyclerView.adapter = adapter
    }

    override fun initData() {

    }

    override fun onFragmentFirstVisible() {
        adapter.data = listData
        adapter.notifyDataSetChanged()
    }

}