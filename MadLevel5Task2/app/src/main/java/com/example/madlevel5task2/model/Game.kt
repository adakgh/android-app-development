package com.example.madlevel5task2.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "gameTable")
@Parcelize
data class Game(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "platform")
    var platform: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    // Auto generating the id. Marked as nullable (?) so it is optional in the constructor.
    var id: Long? = null
) : Parcelable
