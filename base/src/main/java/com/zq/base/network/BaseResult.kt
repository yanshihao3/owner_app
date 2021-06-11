package com.zq.base.network

data class BaseResult<T>(
    val message: String,
    val code: String,
    val data: T
) : IBaseResponse<T> {

    override fun code() = code.toInt()

    override fun msg() = message

    override fun data() = data

    override fun isSuccess() = code == "200"

}