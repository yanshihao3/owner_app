package com.zq.owner.ui.housekeeper

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentHousekeeperBinding
import com.zq.owner.ui.housekeeper.adapter.MessageAdapter
import com.zq.owner.ui.housekeeper.model.*
import com.zq.owner.ui.housekeeper.viewmodel.HouseKeeperViewModel
import com.zq.owner.utils.EmotionKeyboard
import com.zq.owner.utils.SoftKeyBoardListener


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
    private val mEmotionKeyboard by lazy {
        EmotionKeyboard.with(activity)
    }
    private val recyclerView by lazy {
        mDataBind!!.recyclerView
    }
    private val mLlMore by lazy {
        mDataBind!!.llMore

    }
    private val elEmotion by lazy { mDataBind!!.elEmotion }
    private val etContent by lazy { mDataBind!!.etContent }
    private val llContent by lazy { mDataBind!!.llContent }
    private val flEmotionView by lazy { mDataBind!!.flEmotionView }
    private val ivEmo by lazy { mDataBind!!.ivEmo }
    private val ivMore by lazy { mDataBind!!.ivMore }

    override fun initView() {
        val recyclerView = mDataBind!!.recyclerView
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = messageAdapter
        messageAdapter.data = messageList
        mDataBind!!.refreshLayout.setEnableLoadMore(false)
        mDataBind!!.refreshLayout.setOnRefreshListener { loadData() }
        initKeyboardListener()
        initEmotionKeyboard()

    }

    /**
     * 监听键盘弹出
     */
    private fun initKeyboardListener() {
        activity?.let {
            SoftKeyBoardListener.setListener(
                it,
                object : SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
                    override fun keyBoardShow(height: Int) {
                        Log.e("TAG", "keyBoardShow: $height")
                        mEmotionKeyboard.setSoftInputHeight(height)
                    }

                    override fun keyBoardHide(height: Int) {

                    }
                })
        }

    }

    private fun initEmotionKeyboard() {
        elEmotion.attachEditText(etContent)
        mEmotionKeyboard.bindToEditText(etContent)
        mEmotionKeyboard.bindToContent(llContent)
        mEmotionKeyboard.setEmotionLayout(flEmotionView)
        mEmotionKeyboard.bindToEmotionButton(ivEmo, ivMore)
        mEmotionKeyboard.setOnEmotionButtonOnClickListener { view: View ->
            when (view.id) {
                R.id.ivEmo -> {
                    recyclerView.postDelayed({
                        recyclerView.smoothScrollToPosition(messageList.size - 1)

                    }, 50)

                    etContent.clearFocus()

                    if (!elEmotion.isShown) {
                        if (mLlMore.isShown) {
                            showEmotionLayout()
                            hideMoreLayout()
                            return@setOnEmotionButtonOnClickListener true
                        }
                    } else if (elEmotion.isShown && !mLlMore.isShown) {
                        ivEmo.setImageResource(R.mipmap.ic_cheat_emo)
                        mEmotionKeyboard.hideSoftInput()
                        return@setOnEmotionButtonOnClickListener false
                    }
                    showEmotionLayout()
                    hideMoreLayout()
                }
                R.id.ivMore -> {
                    recyclerView.postDelayed({
                        recyclerView.smoothScrollToPosition(messageList.size - 1)

                    }, 50)
                    etContent.clearFocus()
                    if (!mLlMore.isShown) {
                        if (elEmotion.isShown) {
                            showMoreLayout()
                            hideEmotionLayout()

                            return@setOnEmotionButtonOnClickListener true
                        }
                    }
                    showMoreLayout()
                    hideEmotionLayout()
                }
            }
            false
        }
    }

    private fun hideEmotionLayout() {
        elEmotion.visibility = View.GONE
        ivEmo.setImageResource(R.mipmap.ic_cheat_emo)

    }

    private fun showMoreLayout() {
        mLlMore.visibility = View.VISIBLE
    }

    private fun showEmotionLayout() {
        elEmotion.visibility = View.VISIBLE
        ivEmo.setImageResource(R.mipmap.ic_cheat_keyboard)
    }

    private fun hideMoreLayout() {
        mLlMore.visibility = View.GONE
    }

    override fun initData() {
        mViewModel.listData.observe(this) {
            messageList.addAll(0, it)
            messageAdapter.notifyDataSetChanged()
            linearLayoutManager.scrollToPositionWithOffset(it.size, 0)
        }
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