package mcssoft.com.roomwordsample.background

import android.content.Context
import androidx.work.Worker
import mcssoft.com.roomwordsample.dao.WordDAO
import mcssoft.com.roomwordsample.database.WordRoomDatabase
import mcssoft.com.roomwordsample.entity.Word

class InsertWorker : Worker() {

    private val context : Context
    private val wordDao : WordDAO

    init {
        context = getApplicationContext()
        wordDao = WordRoomDatabase.getInstance(context)!!.wordDao()
        val str : String = ""
    }

    override fun doWork(): WorkerResult {

        if(wordDao != null) {
            val theWord : String = getInputData().getString("key", "key")
            var word : Word = Word(theWord)
            wordDao.insertWord(word)

            return WorkerResult.SUCCESS
        }

        return WorkerResult.FAILURE
    }
}