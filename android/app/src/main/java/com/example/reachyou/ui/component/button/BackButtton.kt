package com.example.reachyou.ui.component.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.reachyou.R
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick){
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
            contentDescription = "Custom back buttton")
    }
}

@Preview(showBackground = true)
@Composable
fun BackButttonPreview() {
    ReachYouTheme {
//        BackButton()
    }
}