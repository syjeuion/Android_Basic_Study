package com.example.study01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.study01.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReturn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("text",binding.tvNumber.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }

        binding.btnAdd.setOnClickListener {
            var text =binding.tvNumber.text.toString()
            var int:Int
            int = text.toInt() +1
            binding.tvNumber.text = int.toString()
        }
    }
}