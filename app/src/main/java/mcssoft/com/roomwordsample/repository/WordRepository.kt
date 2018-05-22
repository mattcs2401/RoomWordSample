package mcssoft.com.roomwordsample.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import mcssoft.com.roomwordsample.dao.WordDAO
import mcssoft.com.roomwordsample.database.WordRoomDatabase
import mcssoft.com.roomwordsample.entity.Word
import android.os.AsyncTask
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import mcssoft.com.roomwordsample.background.InitWorker
import mcssoft.com.roomwordsample.background.InsertWorker

class WordRepository(application: Application) {

    private val wordDao: WordDAO

    init {
//        val db = WordRoomDatabase.getInstance(application)
        wordDao = WordRoomDatabase.getInstance(application)!!.wordDao()
    }

    private val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    internal fun getAllWords(): LiveData<List<Word>> = allWords

    internal fun insert(word: Word) {
        val data : Data = Data.Builder().putString("key",word.word).build()
        val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(InsertWorker::class.java)
                .setInputData(data)
                .build()
        val workMgr : WorkManager = WorkManager.getInstance()
        return workMgr.enqueue(request)
    }

}