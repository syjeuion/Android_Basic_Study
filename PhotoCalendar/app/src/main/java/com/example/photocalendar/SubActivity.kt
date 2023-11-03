package com.example.photocalendar

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photocalendar.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val byteArray = intent.getByteArrayExtra("img")
//        val bitmap:Bitmap
//        if(byteArray!=null){
//            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//            binding.ivSub.setImageBitmap(bitmap)
//        }
    }
}