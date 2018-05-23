package mcssoft.com.roomwordsample.dao

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import mcssoft.com.roomwordsample.entity.Word

@Dao
internal interface WordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: Word)

    @Query("select * from word_table order by word asc")
    fun getAllWords(): LiveData<MutableList<Word>>

    @Query("delete from word_table")
    fun deleteAll()
}