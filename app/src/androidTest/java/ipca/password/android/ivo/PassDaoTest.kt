package com.example.android.roomwordssample



import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException




@RunWith(AndroidJUnit4::class)
class PassDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var passDao: PassDao
    private lateinit var db: PassRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, PassRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        passDao = db.wordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() {
        val word = Pass("word")
        passDao.insert(word)
        val allWords = passDao.getAlphabetizedWords().waitForValue()
        assertEquals(allWords[0].word, word.word)
    }

    @Test
    @Throws(Exception::class)
    fun getAllWords() {
        val word = Pass("aaa")
        passDao.insert(word)
        val word2 = Pass("bbb")
        passDao.insert(word2)
        val allWords = passDao.getAlphabetizedWords().waitForValue()
        assertEquals(allWords[0].word, word.word)
        assertEquals(allWords[1].word, word2.word)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() {
        val word = Pass("word")
        passDao.insert(word)
        val word2 = Pass("word2")
        passDao.insert(word2)
        passDao.deleteAll()
        val allWords = passDao.getAlphabetizedWords().waitForValue()
        assertTrue(allWords.isEmpty())
    }
}
