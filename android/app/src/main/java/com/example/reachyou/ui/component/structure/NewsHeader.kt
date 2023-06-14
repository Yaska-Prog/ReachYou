package com.example.reachyou.ui.component.structure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.data.local.Article
import com.example.reachyou.ui.component.utils.NewsType
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun NewsHeader(
    news: Article,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(20.dp)
            .clickable { navigateToDetail(news.id) }
    ) {
        AsyncImage(
            model = news.urlGambar,
            contentDescription = "News ke ${news.id}",
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
        )
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = news.title,
            style = MaterialTheme.typography.headlineMedium
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = news.createdAt,
                textAlign = TextAlign.Start
            )
            NewsType(isPositive = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsHeaderPreview() {
    ReachYouTheme {
//        NewsHeader()
    }
}