package com.example.study01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.study01.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? =null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater,container,false)
        Log.d("TAG", "onCreateView: onCreateView")
//        if (savedInstanceState != null) {
//            val text = savedInstanceState.getString("text")
//            binding.etText.hint = text
//            Log.d("TAG", "onCreateView: $text")
//        }
        val text = arguments?.getString("textFromFirst")
        Log.d("TAG", "onCreateView: $text")
        binding.etText.hint = text

        var view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}