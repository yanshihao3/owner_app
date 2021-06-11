package com.zq.owner.ui.notice.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

import com.zq.owner.R
import com.zq.owner.databinding.AppItemNewsBinding
import com.zq.owner.ui.notice.entity.NewsEntity

/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-09 13:32
 **/
class NewsAdapter : BaseQuickAdapter<NewsEntity, BaseViewHolder>(R.layout.app_item_news) {

    /**
     * 当 ViewHolder 创建完毕以后，会执行此回掉
     * 可以在这里做任何你想做的事情
     */
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        // 绑定 view
        DataBindingUtil.bind<AppItemNewsBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: NewsEntity) {
        val binding = DataBindingUtil.getBinding<AppItemNewsBinding>(holder.itemView)
        binding?.setNews(item)
        binding?.news?.setContent("在全球，随着Flutter被越来越多的知名公司应用在自己的商业APP中，" +
                    "Flutter这门新技术也逐渐进入了移动开发者的视野，尤其是当Google在2018年IO大会上发布了第一个" +
                    "Preview版本后，国内刮起来一股学习Flutter的热潮。\n\n为了更好的方便帮助中国开发者了解这门新技术" +
                    "，我们，Flutter中文网，前后发起了Flutter翻译计划、Flutter开源计划，前者主要的任务是翻译" +
                    "Flutter官方文档，后者则主要是开发一些常用的包来丰富Flutter生态，帮助开发者提高开发效率。而时" +
                    "至今日，这两件事取得的效果还都不错！")
    }
}