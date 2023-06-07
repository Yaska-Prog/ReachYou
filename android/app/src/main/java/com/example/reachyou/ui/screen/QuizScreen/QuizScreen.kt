package com.example.reachyou.ui.screen.QuizScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.reachyou.ui.component.ItemQuiz
import com.example.reachyou.ui.component.TopAppBar

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar(title = "Quiz")
        ItemQuiz(
            title = "Tebak bahasa isyarat BISNDO (Mudah)",
            subtitle = "Quiz Bisindo (mudah)",
            totalCoin = 60
        )
        ItemQuiz(
            title = "Tebak bahasa isyarat BISINDO (Sedang)",
            subtitle = "Quiz Bisindo (Sedang)",
            totalCoin = 100
        )
        ItemQuiz(
            title = "Tebak huruf braille",
            subtitle = "Quiz Braille",
            totalCoin = 40
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen()
}