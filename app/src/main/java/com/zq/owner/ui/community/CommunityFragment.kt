package com.zq.owner.ui.community

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.billy.cc.core.component.CC
import com.zq.base.decoration.SpacesItemDecoration

import com.zq.base.fragment.BaseLazyFragment
import com.zq.base.utils.SharedPreferencesUtils
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentCommunityBinding
import com.zq.owner.ui.community.complaint.ComplaintActivity
import com.zq.owner.ui.community.house.HouseActivity
import com.zq.owner.ui.community.pay.CloudPayActivity
import com.zq.owner.ui.community.pay.PayActivity
import com.zq.owner.ui.community.report.ReportActivity
import com.zq.owner.ui.community.report.ReportRecordActivity
import com.zq.owner.ui.community.service.ServiceTelephoneActivity
import com.zq.owner.ui.community.viewmodel.CommunityViewModel
import com.zq.owner.ui.face.FaceActivity
import com.zq.owner.ui.face.FaceResultActivity


class CommunityFragment : BaseLazyFragment<CommunityViewModel, AppFragmentCommunityBinding>() {


    override val layoutId: Int = R.layout.app_fragment_community

    private val adapter by lazy {
        NewsAdapter()
    }

    private val listData = mutableListOf(
        NewsModel("", "", "", ""),
        NewsModel("", "", "", "")
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
        mDataBind.itemPropertyPay.setOnClickListener {
            startActivity(Intent(context, PayActivity::class.java))
        }

//        //测试人脸认证
//        mDataBind.itemHouse.setOnClickListener {
//
//            val string = SharedPreferencesUtils.init(mContext)
//                .getString("face", "")
//            if (string != "") {
//                startActivity(Intent(context, FaceResultActivity::class.java))
//            } else {
//                startActivity(Intent(context, FaceActivity::class.java))
//            }
//        }

        //我的房屋
        mDataBind.itemHouse.setOnClickListener {
            startActivity(Intent(context, ReportRecordActivity::class.java))
        }


        mDataBind.recyclerView.layoutManager = LinearLayoutManager(context)
        mDataBind.recyclerView.adapter = adapter
        val spacesItemDecoration = SpacesItemDecoration(context)
        spacesItemDecoration.setParam(resources.getColor(R.color.line_color), 1f)
        mDataBind.recyclerView.addItemDecoration(spacesItemDecoration)
    }

    override fun initData() {

    }

    override fun onFragmentFirstVisible() {
        adapter.data = listData
        adapter.notifyDataSetChanged()
    }

}

