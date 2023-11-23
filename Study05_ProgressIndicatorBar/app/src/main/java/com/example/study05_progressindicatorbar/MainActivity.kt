package com.example.study05_progressindicatorbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.study05_progressindicatorbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingDialog = LoadingDialog(this)

        showProgress(false)

        binding.btnStartDialog.setOnClickListener {
//            showProgress(true)
            loadingDialog.show()
        }
        binding.btnStopDialog.setOnClickListener {
//            showProgress(false)
            loadingDialog.dismiss()
        }
    }

    fun showProgress(isShow:Boolean){
        if(isShow) binding.progressbar.visibility = View.VISIBLE
        else binding.progressbar.visibility = View.GONE
    }
}