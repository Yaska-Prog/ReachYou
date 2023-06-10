package com.example.reachyou.ui.component.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R

@Composable
fun ItemQuiz(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    totalCoin: Int,
    navigateToDetailQuiz: (Int) -> Unit,
    id: Int
    ) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(180.dp)
        .padding(20.dp)
        .border(
            border = BorderStroke(2.dp, Color.Black)
        )
        .clickable {
            navigateToDetailQuiz(id)
        }

    ){
        Column {
            Box(modifier = modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .background(color = Color(android.graphics.Color.parseColor("#064958")))
            ){
                Text(text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(android.graphics.Color.parseColor("#FFDB58")),
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().padding(10.dp).align(Alignment.Center))
            }
            Row(modifier = modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium)
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = totalCoin.toString(), style = MaterialTheme.typography.bodyMedium)
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.coin_icon), contentDescription = "Coin Icon", tint = Color.Unspecified, modifier = modifier.padding(end = 3.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemQuizPreview() {
//    ItemQuiz(
//        title = "Tebak bahasa isyarat BISINDO (mudah)",
//        subtitle = "Quiz BISINDO (Mudah)",
//        totalCoin = 60
//    )
}