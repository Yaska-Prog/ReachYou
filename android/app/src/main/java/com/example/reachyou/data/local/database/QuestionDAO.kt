package com.example.reachyou.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface QuestionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: List<Question>)

    @Query("SELECT * FROM Question WHERE jenis_quiz = :type order by id ASC")
    fun getQuestionBasedOnType(type: Int): List<Question>
}