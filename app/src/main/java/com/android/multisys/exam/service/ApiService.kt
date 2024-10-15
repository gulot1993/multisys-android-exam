package com.android.multisys.exam.service

import com.android.multisys.exam.base.BaseRedditResponse
import com.android.multisys.exam.base.BaseResponse
import com.android.multisys.exam.data.dto.AuthResponseDTO
import com.android.multisys.exam.data.dto.SubredditDTO
import com.android.multisys.exam.data.dto.SubredditPostDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.time.Duration

interface ApiService {
    @FormUrlEncoded
    @POST("access_token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("scope") scope: String,
        @Field("state") state: String,
        @Field("duration") duration: String
    ): AuthResponseDTO

    @GET("subreddits/popular.json")
    suspend fun getPopular(): BaseResponse<SubredditDTO>

    @GET("subreddits/search.json")
    suspend fun search(
        @Query("q") query: String
    ): BaseResponse<SubredditDTO>

    @GET("r/{subreddit}/new.json")
    suspend fun getSubrreditPost(
        @Path("subreddit") subreddit: String
    ): BaseResponse<SubredditPostDTO>
}