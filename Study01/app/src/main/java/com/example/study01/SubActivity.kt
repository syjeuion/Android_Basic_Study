package com.example.study01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.study01.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = intent.getStringExtra("originalText")
        binding.etText.hint = text

        binding.btnBackMainActivity.setOnClickListener {
            if(binding.etText.text.isNullOrEmpty()){
                goMainActivity(binding.etText.hint.toString())
            }else{
                goMainActivity(binding.etText.text.toString())
            }
        }
    }

    private fun goMainActivity(editText: String){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("editText",editText)
        setResult(RESULT_OK, intent)
        finish()
    }
}