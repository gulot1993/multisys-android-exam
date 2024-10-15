package com.android.multisys.exam.base

data class BaseRedditChildrenResponse<T>(
    val kind: String,
    val data: T
)