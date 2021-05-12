package com.example.madlevel4example

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Defining that this is an entity that needs to be stored in the database.
@Entity(tableName = "reminderTable")
data class Reminder(

    @ColumnInfo(name = "reminder")
    var reminderText: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    // Auto generating the id. Marked as nullable (?) so it is optional in the constructor.
    var id: Long? = null

)