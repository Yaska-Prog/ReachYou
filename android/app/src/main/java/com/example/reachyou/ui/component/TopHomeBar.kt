package com.example.reachyou.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.R
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun TopHomeBar(
    modifier: Modifier = Modifier,
    username: String
) {
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
                    model = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .padding(10.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(text = "Selamat datang, $username", style = MaterialTheme.typography.titleLarge, color = Color.White)
                    Row(modifier = modifier.clip(RoundedCornerShape(20.dp))){
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.coin_icon), contentDescription = "Coin Icon", tint = Color.Unspecified)
                        Spacer(modifier = modifier.width(2.dp))
                        Text(text = "60", style = MaterialTheme.typography.displaySmall, color = Color.White)
                    }
                    Spacer(modifier = modifier.height(15.dp))
                    Text(text = "Selamat Pagi", style = MaterialTheme.typography.titleLarge, color = Color.White)
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
        TopHomeBar(username = "Yaska")
    }
}