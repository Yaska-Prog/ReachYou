package com.example.reachyou.ui.component.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MainCustomDialog(
    title: String,
    subtitle: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    isSuccess: Boolean
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(15.dp),
            modifier = modifier
                .fillMaxWidth(0.85f)
                .border(color = Color.Black, width = 1.dp, shape = RoundedCornerShape(15.dp))
        ) {
            Column(modifier = modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = modifier.height(10.dp))
                Box(modifier = modifier
                    .size(70.dp)
                    .background(
                        color = if(isSuccess) Color.Green else Color.Red,
                        shape = CircleShape
                    ),
                    contentAlignment = Alignment.Center
                ){
                    Icon(imageVector = if(isSuccess) Icons.Default.Done else Icons.Default.Close, contentDescription = "Done Icon", modifier = modifier.size(40.dp), tint = Color.White)
                }
                Text(text = if(isSuccess) "Berhasil!" else "Gagal!", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
                Text(text = if(isSuccess) "Sukses memperbarui data!" else "Gagal memperbarui data!", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                Button(onClick = onConfirm, shape = RoundedCornerShape(20.dp), modifier = modifier
                    .fillMaxWidth()
                    .padding(30.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = if(isSuccess) Color.Green else Color.Red,
                    contentColor = Color.White
                )) {
                    Text(
                        text = "Confirm",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}