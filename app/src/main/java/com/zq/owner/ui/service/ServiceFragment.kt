package com.zq.owner.ui.service

import android.content.Intent
import android.net.Uri
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zq.base.decoration.GridSpaceItemDecoration
import com.zq.base.fragment.BaseLazyFragment
import com.zq.base.utils.SharedPreferencesUtils
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentServiceBinding
import com.zq.owner.ui.community.complaint.ComplaintActivity
import com.zq.owner.ui.community.house.HouseActivity
import com.zq.owner.ui.community.pay.CloudPayActivity
import com.zq.owner.ui.community.report.ReportRecordActivity
import com.zq.owner.ui.face.FaceActivity
import com.zq.owner.ui.face.FaceResultActivity
import com.zq.owner.ui.service.adapter.MenuAdapter
import com.zq.owner.ui.service.circle.CircleActivity
import com.zq.owner.ui.service.entity.MenuItem
import com.zq.owner.ui.service.express.ExpressActivity
import com.zq.owner.ui.service.household.HouseholdActivity
import com.zq.owner.ui.service.live.LiveActivity
import com.zq.owner.ui.service.mail.MallActivity
import com.zq.owner.ui.service.parking.ParkingActivity
import com.zq.owner.ui.service.questionnaire.QuestionnaireActivity
import com.zq.owner.ui.service.viewmodel.ServiceViewModel
import com.zq.owner.ui.service.visitor.VisitorActivity

/**
 * @program: owner_app
 *
 * @description:服务节目
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-08 17:06
 **/
class ServiceFragment : BaseLazyFragment<ServiceViewModel, AppFragmentServiceBinding>() {
    override val layoutId: Int
        get() = R.layout.app_fragment_service
    private val title1 = arrayOf<String>(
        "身份卡", "旅行条", "居住证明", "我的房屋", "住户管理", "车位缴费",
        "访客邀请", "物业缴费", "报事报修", "投诉建议", "专属管家", "投诉热线", "服务电话", "问卷调查"
    )
    private val title2 = arrayOf<String>(
        "社区优先", "社区圈子", "快递查存", "云缴费", "话费充值", "油卡充值"
    )


    private val dataList1 = mutableListOf<MenuItem>()

    private val dataList2 = mutableListOf<MenuItem>()

    private val layoutManager1 by lazy { GridLayoutManager(mContext, 5) }
    private val layoutManager2 by lazy { GridLayoutManager(mContext, 5) }

    private val adapter1 by lazy { MenuAdapter() }
    private val adapter2 by lazy { MenuAdapter() }


    private val dialog by lazy {
        context?.let { BottomSheetDialog(it) }
    }

    override fun onFragmentFirstVisible() {
        for (title in title1) {
            dataList1.add(MenuItem("", title))
        }

        for (title in title2) {
            dataList2.add(MenuItem("", title))
        }
        adapter1.data = dataList1
        adapter2.data = dataList2

    }

    override fun initView() {
        dialog?.setContentView(R.layout.app_dialog_service_item)
        dialog?.findViewById<AppCompatTextView>(R.id.call)?.setOnClickListener {
            callPhone()
            dialog?.dismiss()
        }

        dialog?.findViewById<AppCompatButton>(R.id.cancel)?.setOnClickListener {
            dialog?.dismiss()
        }
        mDataBind.recyclerView.layoutManager = layoutManager1
        mDataBind.recyclerView.adapter = adapter1
        mDataBind.recyclerView.addItemDecoration(GridSpaceItemDecoration(24))
        mDataBind.recyclerView2.layoutManager = layoutManager2
        mDataBind.recyclerView2.adapter = adapter2
        mDataBind.recyclerView2.addItemDecoration(GridSpaceItemDecoration(24))

        adapter1.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> { //身份卡
                    val string = SharedPreferencesUtils.init(mContext)
                        .getString("face", "")
                    if (string != "") {
                        startActivity(Intent(context, FaceResultActivity::class.java))
                    } else {
                        startActivity(Intent(context, FaceActivity::class.java))
                    }
                }
                2 -> startActivity(Intent(context, LiveActivity::class.java)) //居住证明界面
                3 -> startActivity(Intent(context, HouseActivity::class.java)) //我的房屋界面
                4 -> startActivity(Intent(context, HouseholdActivity::class.java)) //住户管理
                5 -> startActivity(Intent(context, ParkingActivity::class.java)) //车位缴费
                6 -> startActivity(Intent(context, VisitorActivity::class.java)) //访客邀请
                8 -> startActivity(Intent(context, ReportRecordActivity::class.java)) //报事报修记录
                9 -> startActivity(Intent(context, ComplaintActivity::class.java)) //投诉建议界面
                11 -> showDialog()
                13 -> startActivity(Intent(context, QuestionnaireActivity::class.java)) //问卷调查
            }
        }

        adapter2.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {
                    startActivity(Intent(context, MallActivity::class.java)) //社区优先
                }
                1 -> startActivity(Intent(context, CircleActivity::class.java)) //社区圈子
                2 -> startActivity(Intent(context, ExpressActivity::class.java)) //快递查询
                3 -> startActivity(Intent(context, CloudPayActivity::class.java)) //云缴费

            }
        }

    }

    private fun callPhone() {
        val uri: Uri = Uri.parse("tel:4000000")
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = uri
        startActivity(intent)
    }

    private fun showDialog() {
        dialog?.apply {
            if (isShowing) {
                dismiss()
            } else {
                show()
            }
        }
    }

    override fun initData() {
    }


}