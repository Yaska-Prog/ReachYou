package com.example.reachyou.ui.screen.register

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.ui.component.button.ActionButton
import com.example.reachyou.ui.component.textfield.EmailTextField
import com.example.reachyou.ui.component.utils.MessageBox
import com.example.reachyou.ui.component.textfield.PasswordTextField
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
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
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            onDismiss = {
                viewModel.onDismissDialog()
                if(viewModel.isSuccess){
                    navigateToSetupProfile()
                }
            },
            onConfirm = {
                viewModel.onDismissDialog()
                if(viewModel.isSuccess){
                    navigateToSetupProfile()
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
                if(password != reTypePassword || password == "" || email == ""){
                    viewModel.isSuccess = false
                    viewModel.isDialogShown = true
                    viewModel.title = "Gagal!"
                    viewModel.subtitle = "Isi data yang diperlukan terlebih dahulu!"
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
                viewModel.isSuccess = true
                viewModel.title = "Sukses!"
                viewModel.subtitle = "Sukses melakukan Register! Silahkan menuju halaman setup profile."
                viewModel.isDialogShown = true
            }
            is UiState.Error -> {
                isLoading = false
                viewModel.isSuccess = false
                viewModel.title = "Gagal!"
                viewModel.subtitle = "Gagal melakukan Register, pesan kesalahan: ${(uiState as UiState.Error).errorMessage}"
                viewModel.isDialogShown = true
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