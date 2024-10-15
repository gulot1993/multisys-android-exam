package com.android.multisys.exam.data.domain

import com.android.multisys.exam.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subreddit(
    val displayName: String,
    val communityIcon: String,
    val description: String,
    val title: String,
    val id: String
): BaseModel
