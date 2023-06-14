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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.ui.component.button.ActionButton
import com.example.reachyou.ui.component.button.BackButton
import com.example.reachyou.ui.theme.ReachYouTheme
import com.example.reachyou.ui.component.inputBox.InputBox
import com.example.reachyou.ui.component.inputBox.UploadBox
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
import com.example.reachyou.ui.utils.UiState
import com.example.reachyou.ui.utils.ViewModelFactory
import com.example.reachyou.ui.utils.reduceFileImage
import com.example.reachyou.ui.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun LaporBugScreen(
    modifier: Modifier = Modifier,
    onBackButtonPressed: () -> Unit,
    viewModel: LaporBugViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getUserInstance(
        LocalContext.current))
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
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

    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            title = viewModel.title,
            subtitle = viewModel.subtitle,
            onDismiss = {
              viewModel.dismissDialog()
                onBackButtonPressed()
            },
            onConfirm = {
                viewModel.dismissDialog()
                onBackButtonPressed()
            },
            isSuccess = viewModel.isSuccess
        )
    }
    when(uiState){
        is UiState.Loading -> {
            isLoading = true
        }
        is UiState.Success -> {
            isLoading = false
            viewModel.title = "Sukses"
            viewModel.subtitle = "Sukses melaporkan bug! Terima kasih banyak atas masukannya"
            viewModel.isSuccess = true
            viewModel.isDialogShown = true
        }
        is UiState.Error -> {
            isLoading = false
            viewModel.title = "Gagal"
            viewModel.subtitle = "Gagal melaporkan bug! Pesan error: ${(uiState as UiState.Error).errorMessage}"
            viewModel.isSuccess = false
            viewModel.isDialogShown = true
        }
        else -> {
            isLoading = false
        }
    }
    Column {
        BackButton(onClick = onBackButtonPressed)
        Text(text = "Laporkan Bug", style = MaterialTheme.typography.titleLarge, modifier = modifier.padding(15.dp))
        Text(text = "Bantu kami untuk menjadi  lebih bermanfaat dengan cara melaporkan bug atau kendala yang anda alami!", style = MaterialTheme.typography.bodyMedium, modifier = modifier.padding(start = 15.dp, end = 15.dp))
        UploadBox(selectedImageByUri = selectedImageByUri, onClick = {
            photoLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        })
        InputBox(
            title = "Email",
            subtitle = "Email yang dapat dihubungi",
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
        ActionButton(text = "Laporkan!", onClick = {
            if(selectedImageByUri != null){
                val file = uriToFile(selectedImageByUri as Uri, context)
                val reduced = reduceFileImage(file)
                val requestImageFile = reduced.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestImageFile
                )
                val emailMultipart = subject.toRequestBody("text/plain".toMediaType())
                val bug = detail.toRequestBody("text/plain".toMediaType())
                viewModel.laporBug(imageMultiPart, emailMultipart, bug)
            }
        }, isLoading = isLoading)
    }
}

@Preview(showBackground = true)
@Composable
fun LaporBugScreenPreview() {
    ReachYouTheme {
//        LaporBugScreen()
    }
}