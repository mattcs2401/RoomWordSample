package mcssoft.com.roomwordsample.background

import androidx.work.Worker
import mcssoft.com.roomwordsample.dao.WordDAO

class WordWorker internal constructor(mDao: WordDAO) : Worker() {

    override fun doWork(): WorkerResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}