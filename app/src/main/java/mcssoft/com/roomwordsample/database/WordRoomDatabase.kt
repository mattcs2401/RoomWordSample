package mcssoft.com.roomwordsample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import mcssoft.com.roomwordsample.entity.Word
import mcssoft.com.roomwordsample.dao.WordDAO
import android.os.AsyncTask.execute
import android.arch.persistence.db.SupportSQLiteDatabase
import android.support.annotation.NonNull
import android.os.AsyncTask
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.ktx.OneTimeWorkRequestBuilder
import mcssoft.com.roomwordsample.background.WordWorker

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    internal abstract fun wordDao(): WordDAO

    companion object {

        @Volatile private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase::class.java, "Words.db")
                            .addCallback(roomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }

        private val roomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
//                PopulateDbAsync(INSTANCE).execute()
                PopulateDB(INSTANCE)
            }
        }
    }

    private class PopulateDB(db: WordRoomDatabase?) {

        lateinit private var mDao: WordDAO

        init {
            if(db != null) {
                mDao = db.wordDao()
            }
        }

//        companion object {
        val wordWorker : WordWorker = WordWorker(mDao)
            val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(WordWorker::class.java).build()
        val workMgr : WorkManager = WorkManager.getInstance()
            val x: Unit = workMgr.enqueue(request)
//        }

    }

    private class PopulateDbAsync(db: WordRoomDatabase?) : AsyncTask<Void, Void, Void>() {

        lateinit private var mDao: WordDAO

        init {
            if(db != null) {
                mDao = db.wordDao()
            }
        }

        override fun doInBackground(vararg params: Void) :Void? {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll()

            var word = Word("Hello")
            mDao.insertWord(word)
            word = Word("World")
            mDao.insertWord(word)

            return null
        }
    }

}

