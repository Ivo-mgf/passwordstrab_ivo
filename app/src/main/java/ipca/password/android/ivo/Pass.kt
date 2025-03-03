

package com.example.android.roomwordssample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "word_table")
data class Pass(
        @PrimaryKey
        @ColumnInfo(name = "word") val word: String,
        @ColumnInfo(name="site") val site: String?,
        @ColumnInfo(name="descricao") val descricao: String?
)
