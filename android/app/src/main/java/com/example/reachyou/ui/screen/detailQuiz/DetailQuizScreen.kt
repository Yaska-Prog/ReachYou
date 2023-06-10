package com.example.reachyou.ui.screen.detailQuiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.data.local.database.Question
import com.example.reachyou.ui.component.button.BackButton
import com.example.reachyou.ui.component.utils.CustomDialogQuiz
import com.example.reachyou.ui.utils.UiState
import com.example.reachyou.ui.utils.ViewModelFactory

@Composable
fun DetailQuizScreen(
    modifier: Modifier = Modifier,
    type: Int,
    navigateToQuiz: () -> Unit,
    viewModel: DetailQuizViewmodel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory.getQuizInstance(
        LocalContext.current
    ))
) {
    viewModel.getListQuestion(type)
    viewModel.getQuestion()
    val uiState by viewModel.uiState.collectAsState()
    BackButton(onClick = {})
    if(viewModel.isDialogShown){
        CustomDialogQuiz(
            onDismiss = {
                viewModel.onDismissDialog()
                navigateToQuiz()
            },
            onConfirm = {
                viewModel.onDismissDialog()
                navigateToQuiz()
            },
            title = "Berhasil",
            subtitle = "Berhasil menyelesaikan quiz, selamat anda benar ${viewModel.totalCorrectAnswer} dari ${viewModel.currentQuestionIndex + 1}"
        )
    }
    when(uiState){
        is UiState.Loading -> {
            CircularProgressIndicator()
        }
        is UiState.Success -> {
            val question = (uiState as UiState.Success<Question>).data
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(android.graphics.Color.parseColor("#CF4FCA")),
                                Color(android.graphics.Color.parseColor("#38E1E1")),
                            )
                        )
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Pertanyaan ke ${viewModel.currentQuestionIndex + 1}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = modifier.padding(bottom = 20.dp)
                )
                AsyncImage(
                    model = question.gambar,
                    contentDescription = "Profile picture",
                )
                Text(
                    text = question.pertanyaan,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(bottom = 30.dp)
                )
                AnswerQuiz(answer = question.jawaban_a, onClick = {
                    viewModel.answerQuestion("A")
                    viewModel.getQuestion()
                })
                AnswerQuiz(answer = question.jawaban_b, onClick = {
                    viewModel.answerQuestion("B")
                    viewModel.getQuestion()
                })
                AnswerQuiz(answer = question.jawaban_c, onClick = {
                    viewModel.answerQuestion("C")
                    viewModel.getQuestion()
                })
                AnswerQuiz(answer = question.jawaban_d, onClick = {
                    viewModel.answerQuestion("D")
                    viewModel.getQuestion()
                })
            }
        }

        is UiState.Error -> TODO()
        is UiState.Idle -> TODO()
        is UiState.Finish -> {
            navigateToQuiz()
        }
    }

}

@Composable
fun AnswerQuiz(
    modifier: Modifier = Modifier,
    answer: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick,
    modifier = modifier
        .fillMaxWidth()
        .height(60.dp)
        .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(text = answer, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerQuizPreview() {
//    DetailQuizScreen()
}