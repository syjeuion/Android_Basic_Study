package com.example.study04_bottomsheets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.study04_bottomsheets.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Expanding BottomSheet
        BottomSheetBehavior.from(binding.coordinator).apply {
//            maxHeight = 700
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        //BottomSheetDialogFragment
//        binding.btn.setOnClickListener {
//            val bottomSheetFragmet = BottomSheetFragment()
//            bottomSheetFragmet.show(supportFragmentManager, bottomSheetFragmet.tag)
//        }

    }
}