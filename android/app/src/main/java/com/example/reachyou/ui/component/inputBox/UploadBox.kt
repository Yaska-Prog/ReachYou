package com.example.reachyou.ui.component.inputBox

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.R
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun UploadBox(
    selectedImageByUri: Uri?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val stroke = Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        .height(100.dp)
        .drawBehind { drawRoundRect(color = Color.Black, style = stroke) }
        .clickable {
            onClick()
        },

        ){
        if(selectedImageByUri == null){
            Row(modifier = modifier.align(Alignment.Center), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.file_present), contentDescription = "File Icon")
                Text(text = "Masukkan media", style = MaterialTheme.typography.bodyMedium, color = Color.Black.copy(alpha = 0.5f))
            }
        }
        AsyncImage(
            model = selectedImageByUri,
            contentDescription = "Choosen Image",
            modifier = modifier.fillMaxSize(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UploadBoxPreview() {
    ReachYouTheme {
        var selectedImageByUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val photoLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageByUri = uri }
        )
        UploadBox(selectedImageByUri, onClick = {
            photoLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        })
    }
}