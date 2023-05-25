package com.example.reachyou.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun ActionButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = true,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = androidx.compose.ui.graphics.Color(android.graphics.Color.parseColor("#FFDB58")),
            contentColor = androidx.compose.ui.graphics.Color.Black
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ButtonLoginPreview() {
    ReachYouTheme {
        ActionButton(text = "Login", modifier = Modifier, onClick = {})
    }
}