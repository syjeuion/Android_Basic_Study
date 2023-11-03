package com.example.study02.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user:User)

    @Query("SELECT * FROM user_data ORDER BY id ASC")
    fun readAllData(): List<User>

//    @Update
//    fun updateData(): List<User>

    @Delete
    fun delete(user:User)
}