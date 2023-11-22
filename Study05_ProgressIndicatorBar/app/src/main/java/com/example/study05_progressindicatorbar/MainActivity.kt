package com.example.study05_progressindicatorbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.study05_progressindicatorbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
    }
}