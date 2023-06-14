package com.example.reachyou.ui.screen.news

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reachyou.R
import com.example.reachyou.data.local.Article
import com.example.reachyou.ui.component.item.ItemNews
import com.example.reachyou.ui.component.structure.NewsHeader
import com.example.reachyou.ui.component.structure.TopAppBar
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
import com.example.reachyou.ui.theme.ReachYouTheme
import com.example.reachyou.ui.utils.UiState
import com.example.reachyou.ui.utils.ViewModelFactory

@Composable
fun NewsScreen(
    modifier: Modifier =  Modifier,
    navigateToCreate: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getNewsInstance(
        LocalContext.current))
) {
//    val list = listOf(1, 2, 3, 4, 5)

    val uiState by viewModel.uiState.collectAsState()
    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            onDismiss = { viewModel.dismissDialog() },
            onConfirm = { viewModel.dismissDialog() },
            isSuccess = false,
            title = "Gagal!",
            subtitle = "Gagal memperbarui data quiz!"
        )
    }
    when (uiState){
        is UiState.Idle -> {
            viewModel.getListArticle()
        }
        is UiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
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
            val list = (uiState as UiState.Success<List<Article>>).data
            val mutableList = list.toMutableList()
            Column {
                TopAppBar(title = "News")
                NewsHeader(news = list[0], navigateToDetail = navigateToDetail)
                mutableList.removeAt(0)
                LazyColumn(modifier = modifier
                    .fillMaxHeight()
                    .padding(20.dp, bottom = 40.dp, end = 20.dp)){
                    items(items = mutableList, itemContent = {item ->
                        ItemNews(item, navigateToDetail)
                        Spacer(modifier = modifier.height(10.dp))
                    })
                }
            }
            Box(modifier = Modifier.fillMaxSize()){
                FloatingActionButton(
                    onClick = navigateToCreate,
                    modifier = Modifier
                        .padding(bottom = 20.dp, end = 15.dp)
                        .align(alignment = Alignment.BottomEnd)
                ) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.custom_fab),
                        contentDescription = "FAB",
                        tint = Color.Unspecified
                    )
                }
            }
        }
        is UiState.Error -> {
            viewModel.showDialog()
        }

        else -> {}
    }

}

@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
    ReachYouTheme {
//        NewsScreen(na)
    }
}