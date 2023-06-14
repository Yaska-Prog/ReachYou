package com.example.reachyou.ui.component.structure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.ui.component.utils.CoinStatus
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun TopHomeBar(
    modifier: Modifier = Modifier,
    sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager(LocalContext.current)
) {
    val shared = sharedPreferenceManager.getUser()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(android.graphics.Color.parseColor("#1C8299")),
                        Color(android.graphics.Color.parseColor("#064958"))
                    )
                )
            )
            .padding(start = 0.dp, bottom = 20.dp, top = 20.dp)

    ){
        Column {
            Row {
                AsyncImage(
                    model = shared?.profileUrl,
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .padding(10.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(text = "Selamat datang", style = MaterialTheme.typography.titleLarge, color = Color.White)
                    CoinStatus(coin = "${shared?.koin}")
                    Spacer(modifier = modifier.height(15.dp))
                    Text(text = "Halo, ${shared?.username}", style = MaterialTheme.typography.titleLarge, color = Color.White)
                    Text(
                        text = "Apa yang anda butuhkan hari ini?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopHomeBarPreview() {
    ReachYouTheme {
    }
}