package com.example.reachyou.ui.component.structure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.ui.component.utils.CoinStatus
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun TopProfileSection(
    modifier: Modifier = Modifier,
    sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager(LocalContext.current)
) {
    val user = sharedPreferenceManager.getUser()
    Row(modifier = modifier.padding(start = 40.dp, end = 40.dp, top = 20.dp, bottom = 20.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(modifier = modifier.padding(15.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = user!!.username, style = MaterialTheme.typography.headlineMedium, modifier = modifier.padding(end = 10.dp ))
                CoinStatus(coin = "60")
            }
            Text(text = "Christian Yaska Natawijaya")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopProfileSectionPreview() {
    ReachYouTheme {
        TopProfileSection()
    }
}