package com.android.multisys.exam.repository

import android.content.SharedPreferences
import com.android.multisys.exam.BuildConfig
import com.android.multisys.exam.data.domain.Subreddit
import com.android.multisys.exam.data.domain.SubredditPost
import com.android.multisys.exam.data.dto.AuthResponseDTO.Companion.toDomain
import com.android.multisys.exam.data.dto.SubredditDTO.Companion.toDomain
import com.android.multisys.exam.data.dto.SubredditPostDTO.Companion.toDomain
import com.android.multisys.exam.service.ApiService
import com.android.multisys.exam.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class RedditRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences
) : RedditRepository {

    override suspend fun getAccessToken(code: String) = flow {
        emit(ResourceState.Loading())
        val response = api.getAccessToken(
            grantType = "authorization_code",
            code = code,
            redirectUri = BuildConfig.REDDIT_REDIRECT_URI,
            state = UUID.randomUUID().toString(),
            scope = "read",
            duration = "temporary"
        ).toDomain()
        sharedPreferences.edit().putString("token", response.accessToken)
        sharedPreferences.edit().apply()
        emit(ResourceState.Success(data = response))
    }.catch {
        emit(ResourceState.Error(message = it.message.toString()))
    }

    override suspend fun getPopular() = flow {
        emit(ResourceState.Loading())
        val popular = api.getPopular().data.children.map { it.data.toDomain() }
        emit(ResourceState.Success(data = popular))
    }.catch {
        emit(ResourceState.Error(message = it.message.toString()))
    }

    override suspend fun search(query: String) = flow {
        emit(ResourceState.Loading())
        val popular = api.search(query = query).data.children.map { it.data.toDomain() }
        emit(ResourceState.Success(data = popular))
    }.catch {
        emit(ResourceState.Error(message = it.message.toString()))
    }

    override suspend fun getSubredditPost(subreddit: String)= flow {
        emit(ResourceState.Loading())
        val popular = api.getSubrreditPost(subreddit = subreddit).data.children.map { it.data.toDomain() }
        emit(ResourceState.Success(data = popular))
    }.catch {
        emit(ResourceState.Error(message = it.message.toString()))
    }

}