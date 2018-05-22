package mcssoft.com.roomwordsample.background

import android.content.Context
import androidx.work.Worker
import mcssoft.com.roomwordsample.dao.WordDAO
import mcssoft.com.roomwordsample.database.WordRoomDatabase
import mcssoft.com.roomwordsample.entity.Word

class WordWorker : Worker() {

    private val context : Context

    init {
        context = getApplicationContext()
    }

    override fun doWork(): WorkerResult {

        val mDao = WordRoomDatabase.getInstance(context)?.wordDao()

        if(mDao != null) {
            mDao.deleteAll()
            var word = Word("Hello")
            mDao.insertWord(word)
            word = Word("World")
            mDao.insertWord(word)

            return WorkerResult.SUCCESS
        }

        return WorkerResult.FAILURE
    }
}