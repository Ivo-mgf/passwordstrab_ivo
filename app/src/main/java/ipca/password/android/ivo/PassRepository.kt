
package com.example.android.roomwordssample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class PassRepository(private val passDao: PassDao) {


    val allWords: LiveData<List<Pass>> = passDao.getAlphabetizedWords()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(pass: Pass) {
        passDao.insert(pass)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(pass: Pass) {
        passDao.delete(pass)
    }
}
