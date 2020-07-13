package com.example.android.roomwordssample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Pass::class], version = 2)
abstract class PassRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): PassDao

    companion object {
        @Volatile
        private var INSTANCE: PassRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): PassRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PassRoomDatabase::class.java,
                        "word_database"
                )

                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance

                instance
            }
        }

    }
}
