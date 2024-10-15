package com.android.multisys.exam.feature.post.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.multisys.exam.R
import com.android.multisys.exam.data.domain.SubredditPost
import com.android.multisys.exam.ui.theme.MultisysandroidexamTheme

@Composable
fun PostListItem(
    onItemClick: () -> Unit,
    post: SubredditPost
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onItemClick()
        },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text (
                modifier = Modifier
                    .fillMaxWidth(),
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Text (
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.post_author, post.author),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                maxLines = 2
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "dark"
)
@Preview(
    showBackground = true
)
@Composable
fun PostListItemPreview() {
    MultisysandroidexamTheme {
        PostListItem(
            onItemClick = {},
            post = SubredditPost(
                author = "Test",
                title = "test"
            )
        )
    }
}