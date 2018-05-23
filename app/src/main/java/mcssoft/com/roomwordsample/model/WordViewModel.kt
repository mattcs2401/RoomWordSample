package mcssoft.com.roomwordsample.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import mcssoft.com.roomwordsample.entity.Word
import mcssoft.com.roomwordsample.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val wordRepository: WordRepository
    private var allWords: LiveData<MutableList<Word>> //= MutableLiveData()

    init {
        wordRepository = WordRepository(application)
        allWords = getAllWords()

    }

    internal fun getAllWords(): LiveData<MutableList<Word>> {
        return wordRepository.getAllWords()
    }

    internal fun insert(word: Word) = wordRepository.insert(word)
}