package com.zq.owner.ui.community

import android.content.Intent
import android.util.Log
import coil.load
import com.billy.cc.core.component.CC
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentCommunityBinding
import com.zq.owner.ui.community.complaint.ComplaintActivity
import com.zq.owner.ui.community.pay.CloudPayActivity
import com.zq.owner.ui.community.report.ReportActivity
import com.zq.owner.ui.community.service.ServiceTelephoneActivity
import com.zq.owner.ui.community.viewmodel.CommunityViewModel
import com.zq.owner.ui.face.FaceActivity


class CommunityFragment : BaseLazyFragment<CommunityViewModel, AppFragmentCommunityBinding>() {


    override val layoutId: Int = R.layout.app_fragment_community

    var imageUrls = listOf(
        "https://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png",
        "https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg",
        "https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg",
        "https://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg"
    )

    override fun initView() {
        //报事报修
        mDataBind.itemRepair.setOnClickListener {
            startActivity(Intent(context, ReportActivity::class.java))
        }
        //云缴费
        mDataBind.itemCloudPay.setOnClickListener {
            startActivity(Intent(context, CloudPayActivity::class.java))
        }
        //服务电话
        mDataBind.itemService.setOnClickListener {
            startActivity(Intent(context, ServiceTelephoneActivity::class.java))
        }
        //投诉建议
        mDataBind.itemComplaint.setOnClickListener {
            startActivity(Intent(context, ComplaintActivity::class.java))
        }

        //测试登录
        mDataBind.itemCommunity.setOnClickListener {
            CC.obtainBuilder("ComponentUser")
                .setActionName("login")
                .build()
                .callAsyncCallbackOnMainThread { cc, result ->
                    Log.e("tag", "initView: ${result.toString()}")
                }
        }

        //测试人脸认证
        mDataBind.itemHouse.setOnClickListener {
            startActivity(Intent(context, FaceActivity::class.java))
        }
    }

    override fun initData() {

    }

    override fun onFragmentFirstVisible() {
        val banner = mDataBind?.banner
        banner?.apply {
            addBannerLifecycleObserver(this@CommunityFragment)
            indicator = CircleIndicator(mContext)
            setAdapter(object : BannerImageAdapter<String>(imageUrls) {
                override fun onBindView(
                    holder: BannerImageHolder,
                    data: String,
                    position: Int,
                    size: Int
                ) {
                    holder.imageView.load(data)
                }
            })
        }
    }

}

