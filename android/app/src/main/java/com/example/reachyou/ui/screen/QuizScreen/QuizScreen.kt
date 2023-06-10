package com.example.reachyou.ui.screen.QuizScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.reachyou.ui.component.item.ItemQuiz
import com.example.reachyou.ui.component.structure.TopAppBar

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    navigateToDetailQuiz: (Int) -> Unit,
    ) {
    Column {
        TopAppBar(title = "Quiz")
        ItemQuiz(
            title = "Tebak bahasa isyarat BISNDO (Mudah)",
            subtitle = "Quiz Bisindo (mudah)",
            totalCoin = 60,
            navigateToDetailQuiz = navigateToDetailQuiz,
            id = 1
        )
        ItemQuiz(
            title = "Tebak bahasa isyarat BISINDO (Sedang)",
            subtitle = "Quiz Bisindo (Sedang)",
            totalCoin = 100,
            navigateToDetailQuiz = navigateToDetailQuiz,
            id = 2
        )
        ItemQuiz(
            title = "Tebak huruf braille",
            subtitle = "Quiz Braille",
            totalCoin = 40,
            navigateToDetailQuiz = navigateToDetailQuiz,
            id = 3
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
//    QuizScreen()
}