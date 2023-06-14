package com.example.reachyou.ui.component.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.ui.utils.UiState
import com.example.reachyou.ui.utils.ViewModelFactory

@Composable
fun BottomSheetEditProfile(
    type: String,
    modifier: Modifier = Modifier,
    dismissBottomSheet: () -> Unit,
    viewModel: BottomSheetViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getUserInstance(
        LocalContext.current
    )),
    sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager(LocalContext.current)
) {
    val uiState by viewModel.uiState.collectAsState()
    var inputEditProfile by rememberSaveable {
        mutableStateOf("")
    }
    if(viewModel.isDialogShown){
        CustomDialogQuiz(onDismiss = {
            viewModel.dismissDialog()
            dismissBottomSheet()
            viewModel.updateUi()
            }, onConfirm = {
            viewModel.dismissDialog()
            dismissBottomSheet()
            viewModel.updateUi()
        },
            isSuccess = viewModel.isPositive,
        title = viewModel.title,
        subtitle = viewModel.subtitle)
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
            val user = sharedPreferenceManager.getUser()
            if(type == "Username"){
                user!!.username = inputEditProfile
                sharedPreferenceManager.saveUser(user)
            }
            else if (type == "Email"){
                user!!.email = inputEditProfile
                sharedPreferenceManager.saveUser(user)
            }
            viewModel.isPositive = true
            viewModel.title = "Berhasil melakukan edit $type"
            viewModel.subtitle = "Berhasil melakukan update profile"
            viewModel.showDialog()
        }
        is UiState.Error -> {
            viewModel.isPositive = false
            viewModel.title = "Gagal melakukan edit $type"
            viewModel.subtitle = "Gagal melakukan update profile"
            viewModel.showDialog()
        }

        else -> {}
    }
    Box(modifier = modifier
        .height(185.dp)
        .fillMaxWidth()){
        Column(modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "Edit $type",
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.headlineMedium)
            OutlinedTextField(value = inputEditProfile,
                onValueChange = {inputEditProfile = it},
                label = { Text(text = type, style = MaterialTheme.typography.bodyMedium)},
                shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp))
            Button(
                onClick = {
                    if(inputEditProfile != ""){
                        viewModel.updateProfile(type, inputEditProfile)
                    }
                    else{
                        viewModel.isPositive = false
                        viewModel.title = "Gagal!"
                        viewModel.subtitle = "Pastikan data sesuai"
                        viewModel.showDialog()
                    }
                },
                enabled = true,
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 16.dp, end = 16.dp, bottom = 3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#FFDB58")),
                    contentColor = Color.Black
                )
            )
            {
                Text(text = "Edit!", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {

}