package com.example.reachyou.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ItemHome(
    val icon: ImageVector,
    val headline: String,
    val subtitle: String,
    val index: Int,
    val navigateToScanner: () -> Unit
)
