package com.example.reachyou.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reachyou.R
import com.example.reachyou.ui.theme.ReachYouTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    input: String,
    output: String,
    onValueChange: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val Lato = FontFamily(
        Font(R.font.lato_black, FontWeight.W700),
        Font(R.font.lato_bold, FontWeight.W600),
        Font(R.font.lato_regular, FontWeight.W400),
        Font(R.font.lato_light, FontWeight.W300),
    )
    OutlinedTextField(
        value = input,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        label = {
            Text(text = "username",
                style = TextStyle(
                    fontFamily = Lato,
                    fontWeight = FontWeight.W300,
                    fontSize = 15.sp,
                ),
                color = Color.Black
            )}
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun CustomTextField() {
    var input by rememberSaveable {
        mutableStateOf("")
    }
    val output by rememberSaveable {
        mutableStateOf("")
    }
    ReachYouTheme {
        CustomTextField(input = input,
            output = output,
            onValueChange = {
            input = it
        })
    }
}