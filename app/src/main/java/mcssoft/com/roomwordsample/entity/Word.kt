package mcssoft.com.roomwordsample.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "word_table")
data class Word(@ColumnInfo(name = "word") var word: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var id: Long = 0

}