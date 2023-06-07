package com.example.reachyou.ui.component.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun NewsType(
    modifier: Modifier = Modifier,
    isPositive: Boolean
) {
    Box(
        modifier = modifier
            .width(80.dp)
            .height(22.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(if (isPositive) Color.Green else Color.Red)
    ){
        Text(text = if(isPositive) "Positif" else "Negatif",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun NewsTypePreview() {
    ReachYouTheme {
        NewsType(isPositive = false)
    }
}