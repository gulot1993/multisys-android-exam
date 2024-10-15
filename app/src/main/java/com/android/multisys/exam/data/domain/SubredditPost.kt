package com.android.multisys.exam.data.domain

import com.android.multisys.exam.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubredditPost(
    val id: String = "",
    val author: String = "",
    val title: String = "",
    val permalink: String? = "",
) : BaseModel
