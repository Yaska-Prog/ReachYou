package com.example.reachyou.ui.component.structure

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.ui.component.utils.CoinStatus
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
import com.example.reachyou.ui.screen.profile.ProfileViewModel
import com.example.reachyou.ui.theme.ReachYouTheme
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
fun TopProfileSection(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getUserInstance(
        LocalContext.current)),
    sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager(LocalContext.current)
) {
    val user = sharedPreferenceManager.getUser()

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if(uri != null){
                val file = uriToFile(uri, context)
                val reduced = reduceFileImage(file)
                val requestImageFile = reduced.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestImageFile
                )
                viewModel.updateProfilePicture(imageMultiPart)
            }
        }
    )
    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            title = viewModel.title,
            subtitle = viewModel.subtitle,
            onDismiss = {
                viewModel.dismissDialog()
            },
            onConfirm = {
                viewModel.dismissDialog()
            },
            isSuccess = viewModel.isSuccess
        )
    }
    when(uiState){
        is UiState.Loading -> {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    strokeWidth = 4.dp,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        is UiState.Success -> {
            viewModel.title = "Sukses"
            viewModel.subtitle = "Sukses melakukan update profile!"
            viewModel.isSuccess = true
            viewModel.isDialogShown = true
        }
        is UiState.Error -> {
            viewModel.title = "Gagal"
            viewModel.subtitle = "Gagal melakukan update profile"
            viewModel.isSuccess = false
            viewModel.isDialogShown = true
        }
        else -> {}
    }

    Row(modifier = modifier.padding(start = 40.dp, end = 40.dp, top = 20.dp, bottom = 20.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = user?.profileUrl,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
                .clickable {
                    photoLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
        )
        Column(modifier = modifier.padding(15.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = user!!.username, style = MaterialTheme.typography.headlineMedium, modifier = modifier.padding(end = 10.dp ))
                CoinStatus(coin = user.koin.toString())
            }
            Text(text = user!!.email)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopProfileSectionPreview() {
    ReachYouTheme {
        TopProfileSection()
    }
}