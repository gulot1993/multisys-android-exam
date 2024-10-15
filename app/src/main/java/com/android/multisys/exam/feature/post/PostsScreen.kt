package com.android.multisys.exam.feature.post

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.multisys.exam.R
import com.android.multisys.exam.data.domain.SubredditPost
import com.android.multisys.exam.feature.post.components.PostListItem
import com.android.multisys.exam.feature.post.state.PostsUIState
import com.android.multisys.exam.feature.utils.LoadingIndicator
import com.android.multisys.exam.ui.theme.MultisysandroidexamTheme
import timber.log.Timber

@Composable
fun PostsScreen(
    subredditTitle: String,
    postViewModel: PostViewModel = hiltViewModel()
) {
    val uiState by postViewModel.uiState.collectAsState()
    LaunchedEffect(key1 = true) {
        postViewModel.getSubredditPosts(subreddit = subredditTitle)
    }
    PostsScreenContent(
        uiState = uiState
    )
}

@Composable
fun PostsScreenContent(
    uiState: PostsUIState
) {
    val posts = uiState.posts
    val isLoading = uiState.isLoading
    val error = uiState.error

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    start = 16.dp,
                    bottom = paddingValues.calculateBottomPadding() + 24.dp,
                    end = 16.dp
                )
        ){
            LoadingIndicator(isLoading = isLoading)
            SubredditPostLists(posts = posts, isDoneLoading = !isLoading)
        }

        if (error.isNotEmpty()) {
            val errorMessage = if (error == "HTTP 404 ") {
                stringResource(R.string.no_results_found)
            } else {
                error
            }
            Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun SubredditPostLists(
    posts: List<SubredditPost>,
    isDoneLoading: Boolean
) {
    if (isDoneLoading && posts.isNotEmpty()) {

        Text(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            text = stringResource(
                id = R.string.related_posts
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = posts, key = { it.id }) {
                PostListItem(
                    post = it,
                    onItemClick = {}
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true
)
@Composable
fun PostsScreenPreview() {
    MultisysandroidexamTheme {
        PostsScreen(
            subredditTitle = ""
        )
    }
}