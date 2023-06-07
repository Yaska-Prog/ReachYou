package com.example.reachyou.ui.component.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MessageBox(
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismiss, title = { Text(text = title)}, text = { Text(text = message)}, confirmButton = {
        Button(onClick = onDismiss) {
            Text(text = "OK")
        }
    })
}