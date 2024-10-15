package com.android.multisys.exam.base

data class BaseRedditResponse<T>(
    val modhash: String,
    val after: String,
    val children: List<BaseRedditChildrenResponse<T>>
)
