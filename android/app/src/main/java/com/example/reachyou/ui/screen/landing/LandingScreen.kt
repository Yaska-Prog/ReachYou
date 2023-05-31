package com.example.reachyou.ui.screen.landing

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.JetReachYouApp
import com.example.reachyou.R
import com.example.reachyou.ui.component.ActionButton
import com.example.reachyou.ui.component.TransparentButton
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(android.graphics.Color.parseColor("#084F5F")),
                        Color(android.graphics.Color.parseColor("#8490AE")),
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(
            id = R.drawable.logo),
            contentDescription = "ReachYou Logo", modifier = Modifier.size(300.dp))
        Spacer(modifier = modifier.height(50.dp))
        ActionButton(text = "Login", onClick = navigateToLogin)
        TransparentButton(onClick = navigateToRegister)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TransparentButtonPreview() {
    ReachYouTheme {
//        LandingScreen()
    }
}