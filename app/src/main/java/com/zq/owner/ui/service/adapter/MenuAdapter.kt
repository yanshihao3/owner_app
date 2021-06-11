package com.zq.owner.ui.service.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.owner.R
import com.zq.owner.databinding.AppItemMenuBinding
import com.zq.owner.ui.service.entity.MenuItem


/**
 * @program: owner_app
 *
 * @description: 菜单适配器
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-08 17:25
 **/
class MenuAdapter() : BaseQuickAdapter<MenuItem, BaseViewHolder>(R.layout.app_item_menu) {


    /**
     * 当 ViewHolder 创建完毕以后，会执行此回掉
     * 可以在这里做任何你想做的事情
     */
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        // 绑定 view
        DataBindingUtil.bind<AppItemMenuBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MenuItem) {
        val binding = DataBindingUtil.getBinding<AppItemMenuBinding>(holder.itemView)
        binding?.menuItem = item
    }
}