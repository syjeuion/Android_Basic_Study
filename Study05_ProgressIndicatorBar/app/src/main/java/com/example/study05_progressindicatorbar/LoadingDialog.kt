package com.example.study05_progressindicatorbar

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.example.study05_progressindicatorbar.databinding.DialogLoadingBinding

class LoadingDialog(context: Context): Dialog(context) {
    private lateinit var binding: DialogLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCancelable(false)

        window!!.setBackgroundDrawable(ColorDrawable())
        window!!.setDimAmount(0.2f)

        binding.btnStop.setOnClickListener {
            this.dismiss()
        }
    }
}