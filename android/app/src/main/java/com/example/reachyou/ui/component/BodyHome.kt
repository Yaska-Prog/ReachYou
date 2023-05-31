package com.example.reachyou.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.model.ItemHome
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun BodyHome(
    modifier: Modifier = Modifier
) {
    val listItem = listOf<ItemHome>(
        ItemHome(icon = ImageVector.vectorResource(id = R.drawable.hand_twofinger), "BISINDO", "Penerjemah BISINDO"),
        ItemHome(icon = ImageVector.vectorResource(id = R.drawable.color_lens), "Warna", "Fitur Pendeteksi Warna"),
        ItemHome(icon = ImageVector.vectorResource(id = R.drawable.uang), "Uang", "Pendeteksi Mata Uang"),
        ItemHome(icon = ImageVector.vectorResource(id = R.drawable.emoji_objects), "Objek", "Pendeteksi Objek benda")
    )
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Text(text = "Fitur", style = MaterialTheme.typography.titleLarge)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
            items(listItem.size){index ->
                ItemHomeUI(
                    icon = listItem[index].icon,
                    headline = listItem[index].headline,
                    subtitle = listItem[index].subtitle
                )
            }
        })
    }
}

@Composable
fun ItemHomeUI(
    icon: ImageVector,
    headline: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
            .padding(10.dp)
            .background(color = Color.White),
    ) {
        Row(modifier = modifier.padding(5.dp)) {
           Icon(imageVector = icon, contentDescription = headline, tint = Color.Unspecified)
           Column {
               Text(text = headline, style = MaterialTheme.typography.headlineMedium)
               Text(text = subtitle, style = MaterialTheme.typography.displaySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemHomePreview() {
    ReachYouTheme {
        BodyHome()
    }
}