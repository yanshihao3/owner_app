package com.zq.owner.ui.community.house

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.owner.R
import com.zq.owner.databinding.AppRecyclerviewPersonItemBinding

/**
 * @program: owner_app
 * @description: 我的房屋中 人员的适配器
 * @author: 闫世豪
 * @create: 2021-06-17 15:12
 */
class PersonAdapter :
    BaseQuickAdapter<PersonModel, BaseViewHolder>(R.layout.app_recyclerview_person_item) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AppRecyclerviewPersonItemBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: PersonModel) {
        val binding = DataBindingUtil.getBinding<AppRecyclerviewPersonItemBinding>(holder.itemView)


    }

}