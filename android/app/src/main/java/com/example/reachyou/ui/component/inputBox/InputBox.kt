package com.example.reachyou.ui.component.inputBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.ui.theme.ReachYouTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBox(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    input: String,
    onValueChange:(String) -> Unit
) {
    Column(
        modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = modifier.height(5.dp))
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.labelSmall,
            placeholder = { Text(text = subtitle, style = MaterialTheme.typography.labelSmall)},
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputBoxPreview() {
    var input by rememberSaveable {
        mutableStateOf("")
    }
    ReachYouTheme {
        InputBox(
            title = "Subject",
            subtitle = "Jelaskan kendala yang anda alami",
            input = input,
            onValueChange = {input = it}
        )
    }
}