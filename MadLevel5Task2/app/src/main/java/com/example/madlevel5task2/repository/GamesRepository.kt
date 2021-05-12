package com.example.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.database.GamesRoomDatabase
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.model.Game

class GamesRepository(context: Context){
    private val gameDao: GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    fun getGames(): LiveData<List<Game>> {
        return gameDao?.getGames()
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

}