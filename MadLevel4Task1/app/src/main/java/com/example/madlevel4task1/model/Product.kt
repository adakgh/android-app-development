package com.example.madlevel4task1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productTable")
data class Product(
    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    // Auto generating the id. Marked as nullable (?) so it is optional in the constructor.
    var id: Long? = null
)
