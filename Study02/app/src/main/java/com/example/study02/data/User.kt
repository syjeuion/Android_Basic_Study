package com.example.study02.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val firstName: String?,
    val lastName: String?,
    val age: String?
)