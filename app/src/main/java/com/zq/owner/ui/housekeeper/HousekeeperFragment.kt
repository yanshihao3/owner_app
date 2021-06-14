package com.zq.owner.ui.housekeeper

import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentHousekeeperBinding
import com.zq.owner.ui.housekeeper.adapter.MessageAdapter
import com.zq.owner.ui.housekeeper.emotiom.EmotionMainFragment
import com.zq.owner.ui.housekeeper.model.*
import com.zq.owner.ui.housekeeper.viewmodel.HouseKeeperViewModel


/**
 * @program: owner_app
 *
 * @description: 管家界面
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-09 18:55
 **/
class HousekeeperFragment :
    BaseLazyFragment<HouseKeeperViewModel, AppFragmentHousekeeperBinding>() {

    override val layoutId: Int = R.layout.app_fragment_housekeeper

    private val messageAdapter by lazy {
        MessageAdapter()
    }

    private val messageList = mutableListOf<Message>()

    private val linearLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    override fun initView() {
        val recyclerView = mDataBind!!.recyclerView
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = messageAdapter
        messageAdapter.data = messageList
        mDataBind!!.refreshLayout.setEnableLoadMore(false)
        mDataBind!!.refreshLayout.setOnRefreshListener { loadData() }


    }


    override fun initData() {
        mViewModel.listData.observe(this) {
            messageList.addAll(0, it)
            messageAdapter.notifyDataSetChanged()
            linearLayoutManager.scrollToPositionWithOffset(it.size, 0)
        }
        initEmotionMainFragment()
    }

    /**
     * 初始化表情面板
     */
    fun initEmotionMainFragment() {

        val emotionMainFragment =
            EmotionMainFragment()
        emotionMainFragment.bindToContentView(mDataBind!!.llContent)
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        // Replace whatever is in thefragment_container view with this fragment,
        // and add the transaction to the backstack
        transaction.replace(R.id.fl_emotionview_main, emotionMainFragment)
        transaction.addToBackStack(null)
        //提交修改
        transaction.commit()
    }

    private fun loadData() {
        mViewModel.load()
        mDataBind!!.refreshLayout.finishRefresh()
    }

    override fun onFragmentFirstVisible() {
        messageList.add(
            Message(
                User("张三", "aaaa"),
                User("李四", "aaaa"),
                MessageDirection.SEND,
                "2021-06-11 12:20",
                "2021-06-11 12:20",
                TextMessage("消息哈哈哈哈哈哈哈哈哈啊啊啊啊啊", ""),
                SentStatus.SENT,
                ReceivedStatus(0)
            )
        )

        for (i in 0..15) {

            messageList.add(
                Message(
                    User("张三", "aaaa"),
                    User("李四", "aaaa"),
                    MessageDirection.RECEIVE,
                    "2021-06-11 12:20",
                    "2021-06-11 12:20",
                    TextMessage("消息哈哈哈哈哈哈哈哈哈啊啊啊啊啊", ""),
                    SentStatus.SENT,
                    ReceivedStatus(0)
                )
            )
        }
        linearLayoutManager.scrollToPositionWithOffset(messageList.size - 1, 0)
    }


}