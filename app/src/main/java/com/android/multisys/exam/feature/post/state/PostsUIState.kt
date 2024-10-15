package com.android.multisys.exam.feature.post.state

import com.android.multisys.exam.data.domain.SubredditPost

data class PostsUIState(
    val isLoading: Boolean = false,
    val error: String = "",
    val posts: List<SubredditPost> = emptyList()
)
