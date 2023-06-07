package com.example.reachyou.ui.component.structure

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth().border(1.dp, Color.Black)){
        Text(text = title, style = MaterialTheme.typography.titleLarge, modifier = modifier.padding(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    ReachYouTheme {
        TopAppBar(title = "News")
    }
}