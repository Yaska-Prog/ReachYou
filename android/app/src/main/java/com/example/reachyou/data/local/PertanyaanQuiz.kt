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

            Question(gambar = "https://storage.googleapis.com/images_quiz/1.png", pertanyaan = "Membentuk kata apakah serangkaian huruf diatas?", jawaban_a = "Semangka", jawaban_b = "Sirsat", jawaban_c = "Srikaya", jawaban_d = "Salak", jawaban_benar = "A", jenis_quiz = 2),
            Question(gambar = "https://storage.googleapis.com/images_quiz/2.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "Jambu", jawaban_b = "Bambu", jawaban_c = "Apel", jawaban_d = "Anggur", jawaban_benar = "C", jenis_quiz = 2),
            Question(gambar = "https://storage.googleapis.com/images_quiz/3.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "Zebu", jawaban_b = "Zorilla", jawaban_c = "Zebra", jawaban_d = "Zuma", jawaban_benar = "C", jenis_quiz = 2),
            Question(gambar = "https://storage.googleapis.com/images_quiz/4.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "Badak", jawaban_b = "Burung", jawaban_c = "Bangau", jawaban_d = "Buldak", jawaban_benar = "B", jenis_quiz = 2),
            Question(gambar = "https://storage.googleapis.com/images_quiz/5.png", pertanyaan = "Membentuk huruf apakah gambar bahasa isyarat diatas?", jawaban_a = "Super", jawaban_b = "Sendal", jawaban_c = "Serial", jawaban_d = "Sandal", jawaban_benar = "D", jenis_quiz = 2),

            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture21.png", pertanyaan = "Membentuk huruf apakah huruf Braille diatas?", jawaban_a = "N", jawaban_b = "J", jawaban_c = "K", jawaban_d = "L", jawaban_benar = "A", jenis_quiz = 3),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture22.png", pertanyaan = "Membentuk huruf apakah huruf Braille diatas?", jawaban_a = "F", jawaban_b = "Z", jawaban_c = "G", jawaban_d = "Q", jawaban_benar = "D", jenis_quiz = 3),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture23.png", pertanyaan = "Membentuk huruf apakah huruf Braille diatas?", jawaban_a = "H", jawaban_b = "G", jawaban_c = "Z", jawaban_d = "M", jawaban_benar = "B", jenis_quiz = 3),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture24.png", pertanyaan = "Membentuk huruf apakah huruf Braille diatas?", jawaban_a = "S", jawaban_b = "U", jawaban_c = "L", jawaban_d = "K", jawaban_benar = "A", jenis_quiz = 3),
            Question(gambar = "https://storage.googleapis.com/images_quiz/Picture25.png", pertanyaan = "Membentuk huruf apakah huruf Braille diatas?", jawaban_a = "L", jawaban_b = "K", jawaban_c = "Z", jawaban_d = "N", jawaban_benar = "C", jenis_quiz = 3),
            )
    }
}