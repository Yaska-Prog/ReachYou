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
import com.example.reachyou.ui.screen.laporBug.LaporBugViewModel
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
fun CreateNewsScreen(
    modifier: Modifier = Modifier,
    navigateToNews: () -> Unit,
    viewModel: CreateNewsViewmodel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getNewsInstance(
        LocalContext.current))
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
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            title = viewModel.title,
            subtitle = viewModel.subtitle,
            onDismiss = {
                if(viewModel.isSuccess){
                    navigateToNews()
                }
                viewModel.dismissDialog()
            },
            onConfirm = {
                if(viewModel.isSuccess){
                    navigateToNews()
                }
                viewModel.dismissDialog()
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
            viewModel.subtitle = "Sukses membuat berita!"
            viewModel.isSuccess = true
            viewModel.isDialogShown = true
        }
        is UiState.Error -> {
            isLoading = false
            viewModel.title = "Gagal"
            viewModel.subtitle = "Gagal membuat berita! Pesan error: ${(uiState as UiState.Error).errorMessage}"
            viewModel.isSuccess = false
            viewModel.isDialogShown = true
        }
        else -> {
            isLoading = false
        }
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
        ActionButton(text = "Laporkan!", onClick = {
           if(selectedImageByUri != null && headline != "" && content != ""){
               val file = uriToFile(selectedImageByUri as Uri, context)
               val reduced = reduceFileImage(file)
               val requestImageFile = reduced.asRequestBody("image/jpeg".toMediaTypeOrNull())
               val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                   "file",
                   file.name,
                   requestImageFile
               )
               val titleMultipart = headline.toRequestBody("text/plain".toMediaType())
               val descriptionMultipart = content.toRequestBody("text/plain".toMediaType())
               viewModel.createNews(imageMultiPart, titleMultipart, descriptionMultipart)
           }
            else{
               viewModel.title = "Gagal!"
               viewModel.subtitle = "Gagal membuat berita! Tolong lengkapi data yang ada terlebih dahulu!"
               viewModel.isSuccess = false
               viewModel.isDialogShown = true
           }
        }, isLoading = isLoading)
    }
}

@Preview(showBackground = true)
@Composable
fun LaporBugScreenPreview() {
    ReachYouTheme {
//        CreateNewsScreen()
    }
}