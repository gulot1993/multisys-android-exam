package com.android.multisys.exam.data.dto

import com.android.multisys.exam.base.BaseModel
import com.android.multisys.exam.data.domain.Subreddit
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubredditDTO(
    @SerializedName("display_name_prefixed")
    val displayName: String? = "",
    @SerializedName("community_icon")
    val communityIcon: String? = "",
    val description: String? = "",
    val title: String? = "",
    val id: String? = ""
): BaseModel {
    companion object {
        fun SubredditDTO.toDomain(): Subreddit {
            return with(this) {
                Subreddit(
                    displayName = displayName.orEmpty(),
                    communityIcon = communityIcon.orEmpty(),
                    description = description.orEmpty(),
                    title = title.orEmpty(),
                    id = id.orEmpty()
                )
            }
        }
    }
}