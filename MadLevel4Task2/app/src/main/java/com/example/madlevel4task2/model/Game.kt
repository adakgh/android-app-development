package com.example.madlevel4task2.model

import androidx.room.*
import java.util.*

@Entity(tableName = "gameTable")
data class Game(
    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "computer_move")
    var computer_move: String,

    @ColumnInfo(name = "player_move")
    var player_move: String,

    @ColumnInfo(name = "result")
    var result: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    // Auto generating the id. Marked as nullable (?) so it is optional in the constructor.
    var id: Long? = null
)
