package com.zq.owner.ui.community.service.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.owner.R
import com.zq.owner.databinding.AppRecyclerviewPhoneItemBinding
import com.zq.owner.ui.community.service.model.PhoneInfo

/**
 * @program: owner_app
 *
 * @description: 服务电话的适配器
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-15 18:10
 **/
class PhoneAdapter :
    BaseQuickAdapter<PhoneInfo, BaseViewHolder>(R.layout.app_recyclerview_phone_item) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        // 绑定 view
        DataBindingUtil.bind<AppRecyclerviewPhoneItemBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: PhoneInfo) {
        val binding = DataBindingUtil.getBinding<AppRecyclerviewPhoneItemBinding>(holder.itemView)
        binding?.phoneInfo = item

    }
}