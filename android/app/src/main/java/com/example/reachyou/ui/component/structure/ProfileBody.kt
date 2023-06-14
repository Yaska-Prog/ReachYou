package com.example.reachyou.ui.component.structure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.ui.component.item.ItemBodyProfile
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
import com.example.reachyou.ui.component.utils.LogoutDialog
import com.example.reachyou.ui.theme.ReachYouTheme

@Composable
fun ProfileBody(
    modifier: Modifier = Modifier,
    openModalBottomSheet: (String) -> Unit,
    navigateToLaporBug: () -> Unit,
    navigateToLanding: () -> Unit,
    sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager(LocalContext.current)
) {
    var isDialogLogoutShown by remember {
        mutableStateOf(false)
    }
    val user = sharedPreferenceManager.getUser()
    if(isDialogLogoutShown){
        LogoutDialog(onDismiss = {
            isDialogLogoutShown = false
            navigateToLanding()
        }, onConfirm = {
            isDialogLogoutShown = false
            navigateToLanding()
        })
    }
    if(user != null){
        Column {
            Column(
                modifier = modifier.padding(20.dp)
            ) {
                Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Profile", style = MaterialTheme.typography.headlineMedium)
                }
                ItemBodyProfile(type = "Username", value = user.username, openModalBottomSheet = openModalBottomSheet)
                ItemBodyProfile(type = "Email", value = user.email, openModalBottomSheet = openModalBottomSheet)
            }
            Divider(thickness = 1.dp, color = Color.Black.copy(alpha = 0.5f))
            Column(
                modifier = modifier.padding(20.dp)
            ) {
                Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Aplikasi", style = MaterialTheme.typography.headlineMedium)
                }
                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clickable {
                            isDialogLogoutShown = true
                            sharedPreferenceManager.logout()
                        },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 15.dp
                    )
                ) {
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.logout),
                            contentDescription = "Logout Icon",
                            tint = Color.Unspecified,
                            modifier = modifier
                                .padding(15.dp)
                                .size(30.dp)
                        )
                        Text(text = "Logout", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clickable { navigateToLaporBug() },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 15.dp
                    )
                ) {
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.danger_icon),
                            contentDescription = "Danger Icon",
                            tint = Color.Unspecified,
                            modifier = modifier
                                .padding(15.dp)
                                .size(30.dp)
                        )
                        Text(text = "Laporkan Bug", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileBodyPreview() {
    ReachYouTheme {
//        ProfileBody()
    }
}