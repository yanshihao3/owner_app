package com.zq.owner.ui.service.circle

import androidx.appcompat.widget.AppCompatImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

import com.zq.owner.R

/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-19 14:25
 *  https://pic.rmb.bdstatic.com/98446379fb92c9e643a385220826f441.jpeg
 * https://img0.baidu.com/it/u=1462207527,3023121436&fm=26&fmt=auto&gp=0.jpg
 **/
class CircleAdapter :
    BaseQuickAdapter<CircleModel, BaseViewHolder>(R.layout.app_recycleview_circle_item) {
    override fun convert(holder: BaseViewHolder, item: CircleModel) {


        holder.getView<AppCompatImageView>(R.id.image1).load("https://pic.rmb.bdstatic.com/98446379fb92c9e643a385220826f441.jpeg")
        holder.getView<AppCompatImageView>(R.id.image2).load("https://img0.baidu.com/it/u=1462207527,3023121436&fm=26&fmt=auto&gp=0.jpg")
    }
}