package com.zq.owner.ui.community.report

import androidx.appcompat.widget.AppCompatImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.base.utils.DisplayUtils
import com.zq.owner.R
import com.zq.owner.ui.community.report.model.ImageInfo
import com.zq.owner.utils.FileUtils

/**
 * @program: owner_app
 *
 * @description:图片的适配器
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-15 13:31
 **/
class ImageAdapter :
    BaseQuickAdapter<ImageInfo, BaseViewHolder>(R.layout.app_recyclerview_iamge_item) {
    var width: Int = 0
    var height = 0

    val defaultMargin = 8f

    init {
        addChildClickViewIds(R.id.image)
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        val size = DisplayUtils.dip2px(context, defaultMargin)
        val screenWidth = DisplayUtils.getWidth(context)
        width = (screenWidth - size) / 3
        height = width
    }

    override fun convert(holder: BaseViewHolder, item: ImageInfo) {
        val imageVIew = holder.getView<AppCompatImageView>(R.id.image)
        imageVIew.layoutParams.width = width
        imageVIew.layoutParams.height = width
        if (item.isShowAdd) {
            imageVIew.setImageResource(R.mipmap.ic_launcher)
        } else {
            imageVIew.load(FileUtils.getImageContentUri(context, item.url))
        }
    }
}