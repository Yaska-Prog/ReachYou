package com.example.reachyou.ui.component.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun CoinStatus(
    modifier: Modifier = Modifier,
    coin: String
) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(20.dp))
        .background(color = Color(android.graphics.Color.parseColor("#084F5F")))
        .padding(start = 15.dp, end = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.coin_icon), contentDescription = "Coin Icon", tint = Color.Unspecified, modifier = modifier.padding(end = 3.dp))
        Spacer(modifier = modifier.width(2.dp))
        Text(text = coin, style = MaterialTheme.typography.displaySmall, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun CoinStatusPreview() {
    ReachYouTheme {
        CoinStatus(coin = "60")
    }
}