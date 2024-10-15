package com.android.multisys.exam.data.dto

import com.android.multisys.exam.BuildConfig
import com.android.multisys.exam.base.BaseModel
import com.android.multisys.exam.data.domain.SubredditPost
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubredditPostDTO(
    val id: String? = "",
    @SerializedName("author_fullname")
    val author: String? = "",
    val title: String? = "",
    val permalink: String? = "",
) : BaseModel {
    companion object {
        fun SubredditPostDTO.toDomain(): SubredditPost {
            return SubredditPost(
                id = id.orEmpty(),
                author = author.orEmpty(),
                title = title.orEmpty(),
                permalink = "${BuildConfig.API_URL}${permalink}"
            )
        }
    }
}
