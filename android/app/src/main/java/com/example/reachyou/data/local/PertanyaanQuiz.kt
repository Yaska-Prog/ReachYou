package com.example.reachyou.data.local

import com.example.reachyou.data.local.database.Question

object PertanyaanQuiz {
    fun getQuizBisindoMudah(): List<Question>{
        return listOf(
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture1.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "D", jawaban_b = "H", jawaban_c = "N", jawaban_d = "B", jawaban_benar = "C", jenis_quiz = 1),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture2.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "H", jawaban_b = "C", jawaban_c = "B", jawaban_d = "Z", jawaban_benar = "D", jenis_quiz = 1),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture3.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "F", jawaban_b = "A", jawaban_c = "H", jawaban_d = "D", jawaban_benar = "A", jenis_quiz = 1),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture4.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "G", jawaban_b = "Y", jawaban_c = "B", jawaban_d = "E", jawaban_benar = "A", jenis_quiz = 1),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture5.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "Z", jawaban_b = "Q", jawaban_c = "S", jawaban_d = "J", jawaban_benar = "C", jenis_quiz = 1),
        )
    }
}