package mcssoft.com.roomwordsample.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import mcssoft.com.roomwordsample.dao.WordDAO
import mcssoft.com.roomwordsample.database.WordRoomDatabase
import mcssoft.com.roomwordsample.entity.Word

class InsertWorker : Worker() {

    private var wordDao : WordDAO? = null

    override fun doWork(): WorkerResult {

        wordDao = WordRoomDatabase.getInstance(applicationContext)!!.wordDao()

        if(wordDao != null) {
            val theWord : String = getInputData().getString("key", "key")
            wordDao!!.insertWord(Word(theWord))

            return WorkerResult.SUCCESS
        }

        return WorkerResult.FAILURE
    }
}