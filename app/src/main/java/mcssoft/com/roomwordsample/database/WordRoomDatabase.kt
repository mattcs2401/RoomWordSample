package mcssoft.com.roomwordsample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import mcssoft.com.roomwordsample.entity.Word
import mcssoft.com.roomwordsample.dao.WordDAO
import android.arch.persistence.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import mcssoft.com.roomwordsample.background.InitWorker

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
                            .addCallback(callback)
                            .build()
                }
            }
            return INSTANCE
        }

        private val callback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // pre-populate database
                val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(InitWorker::class.java).build()
                val workMgr : WorkManager = WorkManager.getInstance()
                return workMgr.enqueue(request)
            }
        }
    }

}

