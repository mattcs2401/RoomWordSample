package mcssoft.com.roomwordsample.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import mcssoft.com.roomwordsample.entity.Word
import mcssoft.com.roomwordsample.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val wordRepository: WordRepository
    private var allWords: LiveData<List<Word>>

    init {
        wordRepository = WordRepository(application)
        allWords = wordRepository.getAllWords()
    }

    //internal val allWords: LiveData<List<Word>> = wordRepository.getAllWords()

    internal fun getAllWords(): LiveData<List<Word>> {
        return wordRepository.getAllWords()
    }

    internal fun insert(word: Word) = wordRepository.insert(word)
}