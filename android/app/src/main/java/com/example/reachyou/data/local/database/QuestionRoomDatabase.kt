package com.example.reachyou.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.reachyou.data.local.PertanyaanQuiz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Question::class], version = 1)
abstract class QuestionRoomDatabase: RoomDatabase() {

    abstract fun questionDao(): QuestionDAO

    companion object{
        @Volatile
        private var INSTANCE: QuestionRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): QuestionRoomDatabase {
            if(INSTANCE == null){
                synchronized(QuestionRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        QuestionRoomDatabase::class.java, "question_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addCallback(object: Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE.let { database ->
                                    applicationScope.launch {
                                        val questionDAO = database!!.questionDao()
                                        questionDAO.insertQuestion(PertanyaanQuiz.getQuizBisindoMudah())
                                    }
                                }
                            }
                        })
                        .build()
                }
            }
            return INSTANCE as QuestionRoomDatabase
        }
    }
}