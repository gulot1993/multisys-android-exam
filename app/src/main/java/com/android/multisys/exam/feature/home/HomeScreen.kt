package com.android.multisys.exam.feature.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.android.multisys.exam.data.domain.Subreddit
import com.android.multisys.exam.feature.home.components.HomeListItem
import com.android.multisys.exam.feature.home.components.TextFieldWithButton
import com.android.multisys.exam.feature.home.state.HomeUIState
import com.android.multisys.exam.feature.utils.LoadingIndicator
import com.android.multisys.exam.ui.theme.MultisysandroidexamTheme

@Composable
fun HomeScreen(
    authorizationCode: String,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (subreddit: String) -> Unit
) {

    val uiState by homeViewModel.uiState.collectAsState()
    LaunchedEffect(key1 = true) {
        homeViewModel.getAccessTokenAndLoadPopular(authorizationCode)
    }
    HomeScreenContent(uiState = uiState, viewModel = homeViewModel, onItemClick = onItemClick)
}

@Composable
fun HomeScreenContent(
    uiState: HomeUIState,
    viewModel: HomeViewModel,
    onItemClick: (subreddit: String) -> Unit
) {
    val isLoading = uiState.isLoading
    val error = uiState.error
    val subreddits = uiState.subreddits
    val isPopular = uiState.isPopular

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
        ) {
            TextFieldWithButton(
                onSearchButtonClick = viewModel::searchSubreddit
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )

            LoadingIndicator(isLoading = isLoading)

            SubRedditLists(isLoadingDone = !isLoading, subreddits = subreddits, isPopular = isPopular, onItemClick = onItemClick)

        }
    }

    if (error.isNotEmpty()) {
        Toast.makeText(LocalContext.current, error, Toast.LENGTH_LONG).show()
    }

}

@Composable
fun SubRedditLists(
    isLoadingDone: Boolean,
    subreddits: List<Subreddit>,
    isPopular: Boolean,
    onItemClick: (subreddit: String) -> Unit
) {
    if (isLoadingDone) {
        Text(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            text = stringResource(
                id = if (subreddits.isNotEmpty()) {
                    if (isPopular)
                        R.string.popular_reddits
                    else
                        R.string.showing_results
                } else {
                    R.string.no_results
                }
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = subreddits, key = { it.id }) {
                HomeListItem(
                    onItemClick = onItemClick,
                    subreddit = it
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
fun HomeScreenPreview() {
    MultisysandroidexamTheme {
        HomeScreen(
            authorizationCode = "",
            onItemClick = {}
        )
    }
}