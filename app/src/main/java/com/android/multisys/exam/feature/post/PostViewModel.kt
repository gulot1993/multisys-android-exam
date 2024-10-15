package com.android.multisys.exam.feature.post

import androidx.lifecycle.viewModelScope
import com.android.multisys.exam.base.BaseViewModel
import com.android.multisys.exam.data.domain.SubredditPost
import com.android.multisys.exam.feature.post.state.PostsUIState
import com.android.multisys.exam.repository.RedditRepository
import com.android.multisys.exam.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor() : BaseViewModel<PostsUIState>() {

    @Inject
    lateinit var repository: RedditRepository

    override val initialState: PostsUIState
        get() = PostsUIState()

    fun getSubredditPosts(subreddit: String) {
        viewModelScope.launch {
            repository
                .getSubredditPost(subreddit)
                .collectLatest { responseState ->
                    observePosts(responseState = responseState)
                }
        }
    }

    private fun observePosts(responseState: ResourceState<List<SubredditPost>>) {
        viewModelScope.launch {
            when(responseState) {
                is ResourceState.Error -> {
                    mutableUiState.value = mutableUiState.value.copy(isLoading = false, error = responseState.message)
                }

                is ResourceState.Loading -> {
                    mutableUiState.value = mutableUiState.value.copy(isLoading = true)
                }

                is ResourceState.Success -> {
                    mutableUiState.value = mutableUiState.value.copy(isLoading = false, posts = responseState.data)
                }
            }
        }
    }
}