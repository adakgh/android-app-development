package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel5task2.model.Game

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gameTable ORDER BY releaseDate asc")
    fun getGames(): LiveData<List<Game>>

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()
}