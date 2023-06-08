package com.example.reachyou.ui.screen.login

import android.content.res.Configuration
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.ui.component.button.ActionButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.model.UserModel
import com.example.reachyou.ui.utils.ViewModelFactory
import com.example.reachyou.ui.component.utils.MessageBox
import com.example.reachyou.ui.component.textfield.PasswordTextField
import com.example.reachyou.ui.theme.ReachYouTheme
import com.example.reachyou.ui.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getUserInstance(
        LocalContext.current)),
    sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager(LocalContext.current)
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var isLoading by rememberSaveable {
        mutableStateOf(false)
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
    if(isSuccessMessagebox){
        MessageBox(title = "Sukses melakukan login!", message = "Silahkan menuju ke halaman home", onDismiss = {
            isSuccessMessagebox = false
            viewModel.updateUiState()
            navigateToHome()
        })
    }
    if(isFailedMessagebox){
        MessageBox(
            title = "Gagal melakukan login!",
            message = "Login gagal! Tolong lakukan login kembali!",
            onDismiss = {
                isFailedMessagebox = false
                viewModel.updateUiState()
            }
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(
            id = R.drawable.logo),
            contentDescription = "ReachYou Logo", modifier = Modifier.size(300.dp))
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White.copy(alpha = 0.72f)
        )
        Spacer(modifier = modifier.height(50.dp))
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
        PasswordTextField(input = password, onValueChange = {password = it}, label = "Password")
        ActionButton(text = "Login", onClick = {
            viewModel.login(username, password)
        }, isLoading = isLoading)
        Text(
            text = "Belum memiliki akun? Daftarkan diri anda disini!",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.clickable { navigateToRegister() }
        )
        when(uiState){
            is UiState.Loading -> {
                isLoading = true
            }
            is UiState.Success -> {
                isLoading = false
                isSuccessMessagebox = true
                message = "Berhasil Login"

                val user = (uiState as UiState.Success<UserModel>).data
                sharedPreferenceManager.saveUser(user = user)
                navigateToHome()
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
fun TransparentButtonPreview() {
    ReachYouTheme {
//        LoginScreen()
    }
}