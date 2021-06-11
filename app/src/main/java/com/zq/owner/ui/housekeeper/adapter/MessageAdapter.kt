package com.zq.owner.ui.housekeeper.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zq.owner.R
import com.zq.owner.databinding.*
import com.zq.owner.ui.housekeeper.model.ImageMessage
import com.zq.owner.ui.housekeeper.model.Message
import com.zq.owner.ui.housekeeper.model.MessageDirection
import com.zq.owner.ui.housekeeper.model.TextMessage

/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-11 13:50
 **/

private const val SEND_TEXT: Int = R.layout.app_item_text_send
private const val RECEIVE_TEXT: Int = R.layout.app_item_text_receive
private const val SEND_IMAGE: Int = R.layout.app_item_image_send
private const val RECEIVE_IMAGE: Int = R.layout.app_item_image_receive

class MessageAdapter : BaseProviderMultiAdapter<Message>() {
    init {
        // 注册 Provider
        addItemProvider(SendTextProvider())
        addItemProvider(ReceiveTextProvider())
        addItemProvider(SendImageProvider())
        addItemProvider(ReceiveImageProvider())
    }

    override fun getItemType(data: List<Message>, position: Int): Int {
        val message = data[position]
        val isSend = message.messageDirection == MessageDirection.SEND
        return when (message.content) {
            is TextMessage -> {
                if (isSend) SEND_TEXT else RECEIVE_TEXT
            }
            is ImageMessage -> {
                if (isSend) SEND_IMAGE else RECEIVE_IMAGE
            }
        }
    }
}

class SendTextProvider : BaseItemProvider<Message>() {

    override val itemViewType: Int = SEND_TEXT

    override val layoutId: Int = R.layout.app_item_text_send

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AppItemTextSendBinding>(viewHolder.itemView)

    }

    override fun convert(helper: BaseViewHolder, item: Message) {
        val binding = DataBindingUtil.getBinding<AppItemTextSendBinding>(helper.itemView)
        binding?.message = item
        when (item.content) {
            is ImageMessage -> {

            }
            is TextMessage -> {
                binding?.content?.text = (item.content as TextMessage).content
            }
        }

    }

}

class ReceiveTextProvider : BaseItemProvider<Message>() {

    override val itemViewType: Int = RECEIVE_TEXT

    override val layoutId: Int = R.layout.app_item_text_receive

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AppItemTextReceiveBinding>(viewHolder.itemView)

    }

    override fun convert(helper: BaseViewHolder, item: Message) {
        val binding = DataBindingUtil.getBinding<AppItemTextReceiveBinding>(helper.itemView)
        binding?.message = item
        when (item.content) {
            is ImageMessage -> {

            }
            is TextMessage -> {
                binding?.content?.text = (item.content as TextMessage).content
            }
        }
    }

}

class SendImageProvider : BaseItemProvider<Message>() {

    override val itemViewType: Int = SEND_IMAGE

    override val layoutId: Int = R.layout.app_item_image_send

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AppItemImageSendBinding>(viewHolder.itemView)

    }

    override fun convert(helper: BaseViewHolder, item: Message) {
        val binding = DataBindingUtil.getBinding<AppItemImageSendBinding>(helper.itemView)
        binding?.message = item
    }

}

class ReceiveImageProvider : BaseItemProvider<Message>() {

    override val itemViewType: Int = RECEIVE_IMAGE

    override val layoutId: Int = R.layout.app_item_image_receive

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AppItemImageReceiveBinding>(viewHolder.itemView)

    }

    override fun convert(helper: BaseViewHolder, item: Message) {
        val binding = DataBindingUtil.getBinding<AppItemImageReceiveBinding>(helper.itemView)
        binding?.message = item

    }

}

