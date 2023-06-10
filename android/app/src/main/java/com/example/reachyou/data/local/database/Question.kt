package com.example.reachyou.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Question")
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "gambar")
    var gambar: String,
    @ColumnInfo(name = "pertanyaan")
    var pertanyaan: String,
    @ColumnInfo(name = "jawaban_a")
    var jawaban_a: String,
    @ColumnInfo(name = "jawaban_b")
    var jawaban_b: String,
    @ColumnInfo(name = "jawaban_c")
    var jawaban_c: String,
    @ColumnInfo(name = "jawaban_d")
    var jawaban_d: String,
    @ColumnInfo(name = "jawaban_benar")
    var jawaban_benar: String,
    @ColumnInfo(name = "jenis_quiz")
    var jenis_quiz: Int,
)