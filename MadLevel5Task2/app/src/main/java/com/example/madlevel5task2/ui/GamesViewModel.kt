package com.example.madlevel5task2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.repository.GamesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GamesViewModel(application: Application) : AndroidViewModel(application) {
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val gamesRepository = GamesRepository(application.applicationContext)

    // A game object of type LiveData which has a game from the repository
    val games: LiveData<List<Game>> = gamesRepository.getGames()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gamesRepository.insertGame(game)
            }
        }
    }

    fun deleteGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gamesRepository.deleteGame(game)
            }
        }
    }

    fun deleteAllGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gamesRepository.deleteAllGames()
            }
        }
    }
}