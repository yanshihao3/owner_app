package com.zq.owner.ui.housekeeper.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zq.base.viewmodel.BaseViewModel
import com.zq.owner.ui.housekeeper.model.*

/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-11 12:06
 **/
class HouseKeeperViewModel : BaseViewModel() {
    val listData = MutableLiveData<List<Message>>()

    override fun load() {
        listData.value = mutableListOf(
            Message(
                User("张三", "aaaa"),
                User("李四", "aaaa"),
                MessageDirection.RECEIVE,
                "2021-06-11 12:20",
                "2021-06-11 12:20",
                TextMessage("我是新添加的消息，你是谁实话实说", ""),
                SentStatus.SENT,
                ReceivedStatus(0)
            ), Message(
                User("张三", "aaaa"),
                User("李四", "aaaa"),
                MessageDirection.RECEIVE,
                "2021-06-11 12:20",
                "2021-06-11 12:20",
                TextMessage("我是新添加的消息，你是谁实话实说", ""),
                SentStatus.SENT,
                ReceivedStatus(0)

            ),
            Message(
                User("张三", "aaaa"),
                User("李四", "aaaa"),
                MessageDirection.RECEIVE,
                "2021-06-11 12:20",
                "2021-06-11 12:20",
                TextMessage("我是新添加的消息，你是谁实话实说", ""),
                SentStatus.SENT,
                ReceivedStatus(0)
            ), Message(
                User("张三", "aaaa"),
                User("李四", "aaaa"),
                MessageDirection.RECEIVE,
                "2021-06-11 12:20",
                "2021-06-11 12:20",
                TextMessage("我是新添加的消息，你是谁实话实说", ""),
                SentStatus.SENT,
                ReceivedStatus(0)

            ),
            Message(
                User("张三", "aaaa"),
                User("李四", "aaaa"),
                MessageDirection.RECEIVE,
                "2021-06-11 12:20",
                "2021-06-11 12:20",
                TextMessage("我是新添加的消息，你是谁实话实说", ""),
                SentStatus.SENT,
                ReceivedStatus(0)
            ), Message(
                User("张三", "aaaa"),
                User("李四", "aaaa"),
                MessageDirection.RECEIVE,
                "2021-06-11 12:20",
                "2021-06-11 12:20",
                TextMessage("我是新添加的消息，你是谁实话实说", ""),
                SentStatus.SENT,
                ReceivedStatus(0)

            )


        )
    }

}