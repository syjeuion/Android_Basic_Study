package com.example.study02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.study02.data.User
import com.example.study02.data.UserDatabase
import com.example.study02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDb = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "database-user"
        )
            .allowMainThreadQueries()
            .build()

        setUserUi(userDb.userDao().readAllData())

        binding.btnButton.setOnClickListener {
            userDb.userDao().addUser(User(
                null,
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etAge.text.toString()
            ))

            setUserUi(userDb.userDao().readAllData())
        }

        binding.btnDeleteAll.setOnClickListener {
            userDb.clearAllTables()
            setUserUi(userDb.userDao().readAllData())
        }
    }

    private fun setUserUi(userList:List<User>){
        binding.tvTextView.text = ""
        for(user in userList){
            val user1 = "User${user.id} \n " +
                        "First Name: ${user.firstName} \n " +
                        "Last Name: ${user.lastName} \n " +
                        "Age: ${user.age}"
            binding.tvTextView.text = binding.tvTextView.text.toString() + "\n \n" +user1
        }
    }
}