package com.zq.owner.ui.community.pay

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.owner.R
import com.zq.owner.databinding.AppRecyclerviewPayInfoItemBinding

/**
 * @program: owner_app
 *
 * @description: 物业缴费的 界面的适配器
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-17 16:44
 **/
class PayInfoAdapter :
    BaseQuickAdapter<PayInfoModel, BaseViewHolder>(R.layout.app_recyclerview_pay_info_item) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AppRecyclerviewPayInfoItemBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: PayInfoModel) {
        val binding = DataBindingUtil.getBinding<AppRecyclerviewPayInfoItemBinding>(holder.itemView)


    }
}