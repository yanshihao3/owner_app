package com.zq.owner.ui.housekeeper.model


/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-11 13:15
 **/
data class Message(
    var sendUser: User,
    var receiverUser: User,
    var messageDirection: MessageDirection,
    var receivedTime: String,
    var sentTime: String,
    var content: MessageContent,
    var sendStatus: SentStatus,
    var receivedStatus: ReceivedStatus
)


enum class MessageDirection {
    SEND, RECEIVE;
}

class ReceivedStatus {
    private var flag = 0
    private var isRead = false
    private var isListened = false
    private var isDownload = false
    private var isRetrieved = false
    private var isMultipleReceive = false

    companion object {
        private const val READ = 1
        private const val LISTENED = 2
        private const val DOWNLOADED = 4
        private const val RETRIEVED = 8
        private const val MULTIPLERECEIVE = 16

    }

    constructor(flag: Int) {
        this.flag = flag
        isRead = flag and 1 == 1
        isListened = flag and 2 == 2
        isDownload = flag and 4 == 4
        isRetrieved = flag and 8 == 8
        isMultipleReceive = flag and 16 == 16
    }


    open fun getFlag(): Int {
        return flag
    }

    open fun isRead(): Boolean {
        return isRead
    }

    open fun setRead() {
        flag = flag or 1
        isRead = true
    }

    open fun isListened(): Boolean {
        return isListened
    }

    open fun setListened() {
        flag = flag or 2
        isListened = true
    }

    open fun isDownload(): Boolean {
        return isDownload
    }

    open fun setDownload() {
        flag = flag or 4
        isDownload = true
    }

    open fun isRetrieved(): Boolean {
        return isRetrieved
    }

    open fun setRetrieved() {
        flag = flag or 8
        isRetrieved = true
    }

    open fun isMultipleReceive(): Boolean {
        return isMultipleReceive
    }

    open fun setMultipleReceive() {
        flag = flag or 16
        isMultipleReceive = true
    }
}

enum class SentStatus {
    SENDING,
    FAILED,
    SENT,
    RECEIVED,
    READ,
    DESTROYED,
    CANCELED;
}

sealed class MessageContent

data class TextMessage(
    val content: String,
    val extra: String
) : MessageContent()

data class ImageMessage(
    val extra: String,
    val mThumUri: String,
    val mLocalUri: String,
    val mRemoteUri: String
) : MessageContent()

