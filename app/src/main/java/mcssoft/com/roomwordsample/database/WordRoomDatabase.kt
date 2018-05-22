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
import androidx.work.*
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
                // pre-populate database
                val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(WordWorker::class.java).build()
                val workMgr : WorkManager = WorkManager.getInstance()
                return workMgr.enqueue(request)

            }
        }
    }

}

