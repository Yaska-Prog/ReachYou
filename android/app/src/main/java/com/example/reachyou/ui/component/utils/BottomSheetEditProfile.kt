package com.example.reachyou.ui.component.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetEditProfile(
    type: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit,
    input: String,
    isLoading: Boolean
) {
    Box(modifier = modifier
        .height(185.dp)
        .fillMaxWidth()){
        Column(modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "Edit $type",
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.headlineMedium)
            OutlinedTextField(value = input,
                onValueChange = onValueChange,
                label = { Text(text = type, style = MaterialTheme.typography.bodyMedium)},
                shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp))
            Button(
                onClick = onClick,
                enabled = true,
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 16.dp, end = 16.dp, bottom = 3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = androidx.compose.ui.graphics.Color(android.graphics.Color.parseColor("#FFDB58")),
                    contentColor = androidx.compose.ui.graphics.Color.Black
                )
            )
            {
                if(isLoading){
                    CircularProgressIndicator(modifier = modifier.size(16.dp))
                }
                else{
                    Text(text = "Edit!", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    BottomSheetEditProfile(
        type = "Username",
        onClick = { /*TODO*/ },
        onValueChange = { username -> },
        input = "",
        isLoading = false
    )
}