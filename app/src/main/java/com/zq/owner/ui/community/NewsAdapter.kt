package com.zq.owner.ui.community

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.owner.R
import com.zq.owner.databinding.AppRecyclerviewNewsItemBinding

/**
 * @program: owner_app
 *
 * @description:首页中 生活资讯
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-17 13:52
 **/
class NewsAdapter :
    BaseQuickAdapter<NewsModel, BaseViewHolder>(R.layout.app_recyclerview_news_item) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AppRecyclerviewNewsItemBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: NewsModel) {
        val binding = DataBindingUtil.getBinding<AppRecyclerviewNewsItemBinding>(holder.itemView)
        binding?.item = item

    }
}