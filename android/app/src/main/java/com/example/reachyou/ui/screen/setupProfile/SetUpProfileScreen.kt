package com.example.reachyou.ui.screen.setupProfile

import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.R
import com.example.reachyou.ui.component.button.ActionButton
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
import com.example.reachyou.ui.component.utils.MessageBox
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupProfileScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    viewModel: SetUpProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getUserInstance(
        LocalContext.current))
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    val uiState by viewModel.uiState.collectAsState()
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            onDismiss = {
                viewModel.onDismissDialog()
                if(viewModel.isSuccess){
                    navigateToLogin()
                }
            },
            onConfirm = {
                viewModel.onDismissDialog()
                if(viewModel.isSuccess){
                    navigateToLogin()
                }
            },
            isSuccess = viewModel.isSuccess,
            title = viewModel.title,
            subtitle = viewModel.subtitle
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(android.graphics.Color.parseColor("#084F5F")),
                        Color(android.graphics.Color.parseColor("#8490AE")),
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var selectedImageByUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val photoLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageByUri = uri }
        )
        Image(painter = painterResource(
            id = R.drawable.logo),
            contentDescription = "ReachYou Logo",
            modifier = Modifier
                .size(300.dp)
                .padding(top = 60.dp),
            )
        Text(
            text = "Setup Profile",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White.copy(alpha = 0.72f),
            modifier = modifier.padding(bottom = 16.dp)
        )
        AsyncImage(
            model = if(selectedImageByUri == null) "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" else selectedImageByUri,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(70.dp)
                .clip(CircleShape)
                .clickable {
                    photoLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = {username = it},
            shape = RoundedCornerShape(19.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White.copy(0.2f),
                unfocusedContainerColor = Color.White.copy(0.2f)
            ),
            label = {
                Text(text = "Username",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        ActionButton(text = "Continue", onClick = {
            if(selectedImageByUri != null && username != ""){
                val file = uriToFile(selectedImg = selectedImageByUri as Uri, context = context)
                val reducedFile = reduceFileImage(file)
                val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultiPart : MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestImageFile
                )
                val usernameMultipart = username.toRequestBody("text/plain".toMediaType())
                viewModel.setupProfile(usernameMultipart, imageMultiPart)
            }
            else{
                viewModel.isSuccess = false
                viewModel.title = "Gagal!"
                viewModel.subtitle = "Gagal melakukan Set up profile, Tolong isikan data yang diperlukan"
                viewModel.isDialogShown = true
            }
        }, isLoading = isLoading)
        when(uiState){
            is UiState.Loading -> {
                isLoading = true
            }
            is UiState.Success -> {
                isLoading = false
                viewModel.isSuccess = true
                viewModel.title = "Sukses!"
                viewModel.subtitle = "Sukses melakukan Set up profile! Silahkan kembali ke menu login."
                viewModel.isDialogShown = true
            }
            is UiState.Error -> {
                isLoading = false
                viewModel.isSuccess = false
                viewModel.title = "Gagal!"
                viewModel.subtitle = "Gagal melakukan Set up profile, pesan kesalahan: ${(uiState as UiState.Error).errorMessage}"
                viewModel.isDialogShown = true
            }
            else -> {

            }
        }
    }
}
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SetupProfileScreenPreview() {
    ReachYouTheme {
//        SetupProfileScreen()
    }
}