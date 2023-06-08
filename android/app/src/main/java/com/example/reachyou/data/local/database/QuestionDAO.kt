package com.example.reachyou.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface QuestionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: Question)

    @Query("SELECT * FROM Question WHERE jenis_quiz = :type")
    fun getQuestionBasedOnType(type: String): List<Question>
}