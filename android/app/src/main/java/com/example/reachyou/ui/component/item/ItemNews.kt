package com.example.reachyou.ui.component.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.data.local.Article
import com.example.reachyou.ui.component.utils.NewsType
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun ItemNews(
    news: Article,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = modifier.padding(20.dp)
                .clickable { navigateToDetail(news.id) }
        ) {
            AsyncImage(
                model = news.urlGambar,
                contentDescription = news.id.toString(),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(70.dp)
                    .clip(
                        shape = RoundedCornerShape(10.dp)
                    )
            )
            Spacer(modifier = modifier.width(20.dp))
            Column {
                Row(
                    modifier = modifier.fillMaxWidth().padding(bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NewsType(isPositive = true)
                    Text(text = news.createdAt, style = MaterialTheme.typography.labelSmall)
                }
                Text(text = news.title, style = MaterialTheme.typography.bodyMedium)
                Row(
                    modifier = modifier.padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = news.urlGambar,
                        contentDescription = "Profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(20.dp)
                            .clip(CircleShape)
                    )
                    Text(text = news.username, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemNewsPreview() {
    ReachYouTheme {
//        ItemNews()
    }
}