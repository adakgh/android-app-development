package com.example.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.utils.Converters

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GamesRoomDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAMES_DATABASE"

        @Volatile
        private var INSTANCE: GamesRoomDatabase? = null

        fun getDatabase(context: Context): GamesRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(GamesRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GamesRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}