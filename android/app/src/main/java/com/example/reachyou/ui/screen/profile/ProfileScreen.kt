package com.example.reachyou.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.ui.component.structure.ProfileBody
import com.example.reachyou.ui.component.structure.TopProfileSection
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column {
        TopProfileSection()
        Divider(thickness = 1.dp, color = Color.Black.copy(alpha = 0.5f))
        ProfileBody()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ReachYouTheme {
        ProfileScreen()
    }
}