package com.example.reachyou.ui.screen.createNews

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.ui.component.button.ActionButton
import com.example.reachyou.ui.component.button.BackButton
import com.example.reachyou.ui.theme.ReachYouTheme
import com.example.reachyou.ui.component.inputBox.InputBox
import com.example.reachyou.ui.component.inputBox.UploadBox

@Composable
fun CreateNewsScreen(
    modifier: Modifier = Modifier,
    navigateToNews: () -> Unit
) {
    var selectedImageByUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageByUri = uri }
    )
    var headline by rememberSaveable{
        mutableStateOf("")
    }
    var content by rememberSaveable{
        mutableStateOf("")
    }
    Column {
        BackButton(onClick = navigateToNews)
        Text(text = "Tulis beritamu sendiri!", style = MaterialTheme.typography.titleLarge, modifier = modifier.padding(15.dp))
        Spacer(modifier = modifier.height(10.dp))
        Text(text = "Tolong jangan menuliskan berita yang mengandung SARA", style = MaterialTheme.typography.bodyMedium, modifier = modifier.padding(start = 15.dp, end = 15.dp))
        Spacer(modifier = modifier.height(5.dp))
        UploadBox(selectedImageByUri = selectedImageByUri, onClick = {
            photoLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        })
        InputBox(
            title = "Headline berita",
            subtitle = "Tuliskan judul dari berita yang akan kamu angkat",
            input = headline,
            onValueChange = { headline = it }
        )
        InputBox(
            title = "Isi berita",
            subtitle = "Jelaskan secara detail apa saja yang akan menjadi inti berita...",
            input = content,
            onValueChange = { content = it }
        )
        Spacer(modifier = modifier.height(10.dp))
        ActionButton(text = "Laporkan!", onClick = {}, isLoading = false)
    }
}

@Preview(showBackground = true)
@Composable
fun LaporBugScreenPreview() {
    ReachYouTheme {
//        CreateNewsScreen()
    }
}