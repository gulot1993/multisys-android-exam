package com.android.multisys.exam.feature.home.state

import com.android.multisys.exam.data.domain.Subreddit

data class HomeUIState(
    val isLoading: Boolean = true,
    val error: String = "",
    val subreddits: List<Subreddit> = emptyList(),
    val isPopular: Boolean = true
)