package mcssoft.com.roomwordsample.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import mcssoft.com.roomwordsample.dao.WordDAO
import mcssoft.com.roomwordsample.database.WordRoomDatabase
import mcssoft.com.roomwordsample.entity.Word
import android.os.AsyncTask

class WordRepository(application: Application) {

    private val wordDao: WordDAO

    init {
        val db = WordRoomDatabase.getInstance(application)
        wordDao = db!!.wordDao()
    }

    private val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    internal fun getAllWords(): LiveData<List<Word>> = allWords

    internal fun insert(word: Word) {
        insertAsyncTask(wordDao).execute(word)
    }

    private class insertAsyncTask(val asyncTaskDao: WordDAO) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            asyncTaskDao.insertWord(params[0])
            return null
        }
    }

}