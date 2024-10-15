package com.android.multisys.exam.feature.home

import androidx.lifecycle.viewModelScope
import com.android.multisys.exam.base.BaseViewModel
import com.android.multisys.exam.data.domain.Subreddit
import com.android.multisys.exam.feature.home.state.HomeUIState
import com.android.multisys.exam.repository.RedditRepository
import com.android.multisys.exam.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeUIState>(){
    override val initialState: HomeUIState
        get() = HomeUIState()

    @Inject
    lateinit var repository: RedditRepository

    fun getAccessTokenAndLoadPopular(code: String) {
        viewModelScope.launch {
            repository
                .getAccessToken(code)
                .debounce { 500 }
                .flatMapMerge {
                    repository.getPopular()
                }
                .collectLatest { popularSubreddits ->
                    observeSubreddits(popularSubreddits, true)
                }
        }
    }

    fun searchSubreddit(query: String) {
        viewModelScope.launch {
            repository
                .search(query = query)
                .collectLatest { responseState ->
                    observeSubreddits(responseState)
                }
        }
    }

    private fun observeSubreddits(responseState: ResourceState<List<Subreddit>>, isPopular: Boolean = false) {
        viewModelScope.launch {
            when(responseState) {
                is ResourceState.Error -> {
                    mutableUiState.value = mutableUiState.value.copy(isLoading = false, error = responseState.message)
                }

                is ResourceState.Loading -> {
                    mutableUiState.value = mutableUiState.value.copy(isLoading = true)
                }

                is ResourceState.Success -> {
                    mutableUiState.value = mutableUiState.value.copy(isLoading = false, subreddits = responseState.data, isPopular = isPopular)
                }
            }
        }
    }
}