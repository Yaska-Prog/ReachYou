package com.example.reachyou.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.ui.component.structure.BodyHome
import com.example.reachyou.ui.component.structure.TopHomeBar
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToScanner: (Int) -> Unit,
    navigateToBisindo: () -> Unit,
    navigateToMoney: () -> Unit,
) {
    Column {
        TopHomeBar()
        BodyHome(navigateToScanner = navigateToScanner, navigateToBisindo = navigateToBisindo, navigateToMoney = navigateToMoney)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ReachYouTheme {
//        HomeScreen()
    }
}