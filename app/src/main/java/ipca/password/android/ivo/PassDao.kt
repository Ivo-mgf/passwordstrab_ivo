

package com.example.android.roomwordssample

import androidx.lifecycle.LiveData;
import androidx.room.*


@Dao
interface PassDao {


    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Pass>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pass: Pass)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Delete fun delete (word: Pass)
}
