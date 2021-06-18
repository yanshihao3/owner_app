package com.zq.owner.ui.community.report

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.owner.R
import com.zq.owner.databinding.AppRecycleviewReportRecordItemBinding
import com.zq.owner.ui.community.report.model.RecordModel

/**
 * @program: owner_app
 *
 * @description: 报事报修记录的适配器
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-18 10:25
 **/
class RecordAdapter :
    BaseQuickAdapter<RecordModel, BaseViewHolder>(R.layout.app_recycleview_report_record_item) {


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        // 绑定 view
        DataBindingUtil.bind<AppRecycleviewReportRecordItemBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: RecordModel) {
        val binding =
            DataBindingUtil.getBinding<AppRecycleviewReportRecordItemBinding>(holder.itemView)
        when (item.type) {
            "unhandle" -> {
                binding?.iamge?.setImageResource(R.mipmap.app_report_record_unprocessed)
            }
            "inhandle" -> {
                binding?.iamge?.setImageResource(R.mipmap.app_report_record_inprocess)

            }
            "finish" -> {
                binding?.iamge?.setImageResource(R.mipmap.app_report_record_finish)
            }
        }
        binding?.expand?.setContent(
            "具体事件描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述…"
        )
    }
}