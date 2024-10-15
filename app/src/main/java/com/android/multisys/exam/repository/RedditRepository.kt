package com.android.multisys.exam.repository

import com.android.multisys.exam.data.domain.AuthResponse
import com.android.multisys.exam.data.domain.Subreddit
import com.android.multisys.exam.data.domain.SubredditPost
import com.android.multisys.exam.utils.ResourceState
import kotlinx.coroutines.flow.Flow

interface RedditRepository {
    suspend fun getAccessToken(code: String): Flow<ResourceState<AuthResponse>>
    suspend fun getPopular(): Flow<ResourceState<List<Subreddit>>>
    suspend fun search(query: String): Flow<ResourceState<List<Subreddit>>>
    suspend fun getSubredditPost(subreddit: String): Flow<ResourceState<List<SubredditPost>>>
}