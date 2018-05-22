package mcssoft.com.roomwordsample.background

import android.content.Context
import androidx.work.Worker
import mcssoft.com.roomwordsample.dao.WordDAO
import mcssoft.com.roomwordsample.database.WordRoomDatabase
import mcssoft.com.roomwordsample.entity.Word

class InitWorker : Worker() {

    private val context : Context
    private val wordDao : WordDAO

    init {
        context = getApplicationContext()
        wordDao = WordRoomDatabase.getInstance(context)!!.wordDao()
    }

    override fun doWork(): WorkerResult {

        if(wordDao != null) {
            wordDao.deleteAll()
            var word = Word("Hello")
            wordDao.insertWord(word)
            word = Word("World")
            wordDao.insertWord(word)

            return WorkerResult.SUCCESS
        }

        return WorkerResult.FAILURE
    }
}