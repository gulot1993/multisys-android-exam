package com.android.multisys.exam.base


data class BaseResponse<T>(
    val kind: String,
    val data: BaseRedditResponse<T>
)