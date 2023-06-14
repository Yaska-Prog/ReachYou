package com.example.reachyou.ui.screen.detailNews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.data.local.Article
import com.example.reachyou.ui.component.button.BackButton
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
import com.example.reachyou.ui.theme.ReachYouTheme
import com.example.reachyou.ui.utils.UiState
import com.example.reachyou.ui.utils.ViewModelFactory

@Composable
fun DetailNewsScreen(
    modifier: Modifier = Modifier,
    navigateToNews: () -> Unit,
    idNews: Int,
    viewModel: DetailNewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getNewsInstance(
        LocalContext.current))
) {
    val uiState by viewModel.uiState.collectAsState()
    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            onDismiss = { viewModel.dismissDialog() },
            onConfirm = { viewModel.dismissDialog() },
            isSuccess = false,
            subtitle = "Gagal melakukan pembaruan data",
            title = "Gagal!"
        )
    }
    when(uiState){
        is UiState.Idle -> {
            viewModel.getDetailArticle(idNews)
        }
        is UiState.Success -> {
            val news = (uiState as UiState.Success<Article>).data
            Box(
                modifier = modifier.fillMaxSize(),
            ){
                AsyncImage(
                    model = news.urlGambar,
                    contentDescription = "image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                )
                BackButton(onClick = navigateToNews)
                Box(modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 280.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)){
                    Column(
                        modifier = modifier
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = news.profil,
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = modifier.width(10.dp))
                            Text(text = news.username, style = MaterialTheme.typography.headlineMedium)
                        }
                        Spacer(modifier = modifier.height(20.dp))
                        Text(text = news.title, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
                        Spacer(modifier = modifier.height(10.dp))

                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            text = news.description
                        )
                    }
                }
            }
        }
        is UiState.Error -> {
            viewModel.showDialog()
        }

        else -> {}
    }

}

@Preview
@Composable
fun DetailNewsPreview() {
    ReachYouTheme {
//        DetailNewsScreen()
    }
}