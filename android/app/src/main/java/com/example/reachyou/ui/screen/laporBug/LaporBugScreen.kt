package com.example.reachyou.ui.screen.laporBug


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
fun LaporBugScreen(
    modifier: Modifier = Modifier
) {
    var selectedImageByUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageByUri = uri }
    )
    var subject by rememberSaveable{
        mutableStateOf("")
    }
    var detail by rememberSaveable{
        mutableStateOf("")
    }

    Column {
        BackButton()
        Text(text = "Laporkan Bug", style = MaterialTheme.typography.titleLarge, modifier = modifier.padding(15.dp))
        Text(text = "Bantu kami untuk menjadi  lebih bermanfaat dengan cara melaporkan bug atau kendala yang anda alami!", style = MaterialTheme.typography.bodyMedium, modifier = modifier.padding(start = 15.dp, end = 15.dp))
        UploadBox(selectedImageByUri = selectedImageByUri, onClick = {
            photoLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
            )
        })
        InputBox(
            title = "Subject(Inti Permasalahan)",
            subtitle = "Jelaskan kendala yang kamu alami",
            input = subject,
            onValueChange = { subject = it }
        )
        InputBox(
            title = "Detail Masalah",
            subtitle = "Jelaskan kendala yang kamu alami secara lebih mendalama",
            input = detail,
            onValueChange = { detail = it }
        )
        Spacer(modifier = modifier.height(20.dp))
        ActionButton(text = "Laporkan!", onClick = {}, isLoading = false)
    }
}

@Preview(showBackground = true)
@Composable
fun LaporBugScreenPreview() {
    ReachYouTheme {
        LaporBugScreen()
    }
}