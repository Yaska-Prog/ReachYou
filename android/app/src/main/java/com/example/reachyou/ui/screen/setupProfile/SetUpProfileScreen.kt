package com.example.reachyou.ui.screen.setupProfile

import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.example.reachyou.ui.component.ActionButton
import com.example.reachyou.ui.component.MessageBox
import com.example.reachyou.ui.screen.register.RegisterScreen
import com.example.reachyou.ui.theme.ReachYouTheme
import com.example.reachyou.ui.utils.UiState
import com.example.reachyou.ui.utils.ViewModelFactory

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
    var isSuccessMessagebox by rememberSaveable {
        mutableStateOf(false)
    }
    var isFailedMessagebox by rememberSaveable {
        mutableStateOf(false)
    }
    var message by rememberSaveable {
        mutableStateOf("")
    }
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
    if(isSuccessMessagebox){
        MessageBox(title = "Sukses melakukan Set up profile!",
            message = "Set up profile berhasil! Silahkan menuju ke halaman login!", onDismiss = {
                isSuccessMessagebox = false
                navigateToLogin()
            })
    }
    if(isFailedMessagebox){
        MessageBox(
            title = "Gagal melakukan Set up profile!",
            message = "Set up profile gagal! Cek kembali data yang ingin di kirimkan!",
            onDismiss = {isFailedMessagebox = false}
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
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.White.copy(0.2f)
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
            if(selectedImageByUri != null){
                viewModel.setupProfile(username, selectedImageByUri)
            }
            else{
                isFailedMessagebox = true
            }
        }, isLoading = isLoading)
        when(uiState){
            is UiState.Loading -> {
                isLoading = true
            }
            is UiState.Success -> {
                isLoading = false
                isSuccessMessagebox = true
                message = (uiState as UiState.Success<String>).data
            }
            is UiState.Error -> {
                isLoading = false
                isFailedMessagebox = true
                message = (uiState as UiState.Error).errorMessage
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