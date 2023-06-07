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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.reachyou.ui.component.button.BackButton

@Composable
fun DetailQuizScreen(
    modifier: Modifier = Modifier
) {
    BackButton()
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
            text = "Pertanyaan 1 dari 10",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = modifier.padding(bottom = 20.dp)
        )
        AsyncImage(
            model = "https://www.howtogeek.com/wp-content/uploads/2022/01/discord-logo-icon.jpg?width=398&trim=1,1&bg-color=000&pad=1,1",
            contentDescription = "Profile picture",
        )
        Text(
            text = "Membentuk huruf apakah gambar diatas?",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(bottom = 30.dp)
        )
        AnswerQuiz(answer = "Samting")
        AnswerQuiz(answer = "B")
        AnswerQuiz(answer = "C")
        AnswerQuiz(answer = "D")
    }
}

@Composable
fun AnswerQuiz(
    modifier: Modifier = Modifier,
    answer: String
) {
    Button(onClick = { /*TODO*/ },
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
    DetailQuizScreen()
}