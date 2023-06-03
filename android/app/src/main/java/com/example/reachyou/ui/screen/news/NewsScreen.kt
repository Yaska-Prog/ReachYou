package com.example.reachyou.ui.screen.news

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.ui.component.ItemNews
import com.example.reachyou.ui.component.NewsHeader
import com.example.reachyou.ui.component.TopAppBar
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun NewsScreen(
    modifier: Modifier =  Modifier
) {
    val list = listOf(1, 2, 3, 4, 5)

    Column {
        TopAppBar(title = "News")
        NewsHeader()
        LazyColumn(modifier = modifier
            .fillMaxHeight()
            .padding(20.dp, bottom = 40.dp, end = 20.dp)){
            items(items = list, itemContent = {item ->
                Log.d("COMPOSE", "Samting")
                ItemNews()
                Spacer(modifier = modifier.height(10.dp))
            })
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(bottom = 50.dp, end = 15.dp)
                .align(alignment = Alignment.BottomEnd)
        ) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.custom_fab),
                contentDescription = "FAB",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
    ReachYouTheme {
        NewsScreen()
    }
}