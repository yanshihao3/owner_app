package com.zq.owner.ui.notice

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.decoration.SpacesItemDecoration
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentNewsBinding
import com.zq.owner.ui.notice.adapter.MessageAdapter
import com.zq.owner.ui.notice.entity.MessageEntity
import com.zq.owner.ui.notice.viewmodel.NewsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MessageFragment : BaseLazyFragment<NewsViewModel, AppFragmentNewsBinding>() {

    private val newsAdapter by lazy {
        MessageAdapter()
    }


    private val listData = mutableListOf<MessageEntity>()

    override fun onFragmentFirstVisible() {
        listData.add(MessageEntity())
        listData.add(MessageEntity())
        listData.add(MessageEntity())
        listData.add(MessageEntity())
        newsAdapter.data = listData
        newsAdapter.notifyDataSetChanged()
    }

    override val layoutId: Int
        get() = R.layout.app_fragment_news

    override fun initView() {
        mDataBind!!.refreshLayout.setEnableLoadMore(false)
        mDataBind!!.refreshLayout.setOnRefreshListener { loadData() }
        mDataBind!!.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val spacesItemDecoration = SpacesItemDecoration(context)
        spacesItemDecoration.setParam(resources.getColor(R.color.line_color), 1f)

        mDataBind!!.recyclerView.addItemDecoration(spacesItemDecoration)

        mDataBind!!.recyclerView.adapter = newsAdapter


    }

    private fun loadData() {
        lifecycleScope.launch {
            delay(2000)
            mDataBind!!.refreshLayout.finishRefresh()
        }
    }

    override fun initData() {

    }


}