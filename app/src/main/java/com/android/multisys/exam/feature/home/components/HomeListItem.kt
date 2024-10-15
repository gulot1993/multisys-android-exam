package com.android.multisys.exam.feature.home.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.multisys.exam.data.domain.Subreddit
import com.android.multisys.exam.ui.theme.MultisysandroidexamTheme

@Composable
fun HomeListItem(
    onItemClick: (subreddit: String) -> Unit,
    subreddit: Subreddit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onItemClick(subreddit.title)
        },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.onSurface),
                model = "https://styles.redditmedia.com/t5_2w844/styles/communityIcon_krq4riav5m191.png?width=256&amp;s=3bb045009d2a9d1d7543dc7afb7b53a0e6f18121",
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text (
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = subreddit.displayName,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                Text (
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = subreddit.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    maxLines = 2
                )
            }
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
fun HomeListItemPreview() {
    MultisysandroidexamTheme {
        HomeListItem(
            onItemClick = {},
            subreddit = Subreddit(
                displayName = "",
                communityIcon = "",
                description = "",
                id = "",
                title = ""
            )
        )
    }
}