package com.example.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()

}
