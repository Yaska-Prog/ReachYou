package com.example.reachyou.ui.screen.register

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.ui.component.ActionButton
import com.example.reachyou.ui.component.EmailTextField
import com.example.reachyou.ui.component.MessageBox
import com.example.reachyou.ui.component.PasswordTextField
import com.example.reachyou.ui.screen.login.LoginScreen
import com.example.reachyou.ui.theme.ReachYouTheme
import com.example.reachyou.ui.utils.UiState
import com.example.reachyou.ui.utils.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewmodel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getUserInstance(
        LocalContext.current
    )),
    navigateToSetupProfile: () -> Unit,
    navigateToLogin: () -> Unit
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var reTypePassword by rememberSaveable {
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
        MessageBox(title = "Sukses melakukan register!",
            message = "Register berhasil! Silahkan menuju ke halaman login", onDismiss = {
            isSuccessMessagebox = false
                navigateToSetupProfile()
        })
    }
    if(isFailedMessagebox){
        MessageBox(
            title = "Gagal melakukan register!",
            message = "Register gagal! Cek kembali data yang ingin di kirimkan!",
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(
            id = R.drawable.logo),
            contentDescription = "ReachYou Logo", modifier = Modifier.size(300.dp))
        Text(
            text = "Register",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White.copy(alpha = 0.72f)
        )
        Spacer(modifier = modifier.height(50.dp))
        EmailTextField(
            input = email,
            onValueChange = {email = it},
            label = "Email")
        PasswordTextField(input = password, onValueChange = { password = it }, label = "Password")
        PasswordTextField(
            input = reTypePassword,
            onValueChange = { reTypePassword = it },
            label = "Re-Type Password"
        )
        Spacer(modifier = Modifier.height(24.dp))
        ActionButton(text = "Register",
            onClick = {
                if(password != reTypePassword){
                    isFailedMessagebox = true
                }
                else{
                    viewModel.register(email, password)
                }
            },
            isLoading = isLoading)
        Text(
            text = "Sudah memiliki akun? Login akun anda disini!",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.clickable { navigateToLogin() }
        )
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
fun RegisterScreenPreview() {
    ReachYouTheme {
//        RegisterScreen()
    }
}