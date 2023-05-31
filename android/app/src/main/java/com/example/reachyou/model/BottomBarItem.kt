package com.example.reachyou.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.reachyou.ui.navigation.Screen

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
