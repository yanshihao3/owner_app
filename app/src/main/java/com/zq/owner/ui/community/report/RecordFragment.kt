package com.zq.owner.ui.community.report

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.decoration.SpacesItemDecoration
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentReportRecordBinding
import com.zq.owner.ui.community.report.model.RecordModel

private const val ARG_PARAM = "type"

class RecordFragment : BaseLazyFragment<RecordViewModel, AppFragmentReportRecordBinding>() {

    private var type: String? = null

    override val layoutId: Int = R.layout.app_fragment_report_record

    private val adapter by lazy {
        RecordAdapter()
    }
    private val linearLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    private val listData = arrayListOf(
        RecordModel("unhandle", "", "", ""),
        RecordModel("inhandle", "", "", ""),
        RecordModel("finish", "", "", ""),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(ARG_PARAM)
        }
    }

    override fun onFragmentFirstVisible() {
        adapter.data = listData
        adapter.notifyDataSetChanged()

    }

    override fun initView() {
        mDataBind.recyclerView.layoutManager = linearLayoutManager
        mDataBind.recyclerView.adapter = adapter
        mDataBind.recyclerView.addItemDecoration(
            SpacesItemDecoration(context).setParam(
                resources.getColor(R.color.line_color),
                1f
            )
        )
    }

    override fun initData() {

    }

    companion object {

        @JvmStatic
        fun newInstance(type: String) =
            RecordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, type)
                }
            }
    }
}