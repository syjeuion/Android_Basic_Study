package com.example.study01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.study01.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? =null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater,container,false)

        binding.btnGoSubActivity.setOnClickListener {
            (activity as MainActivity).goSubActivity(binding.tvPrinted.text.toString())
        }

        var view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setText(text:String){
        binding.tvPrinted.text = text
    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putString("text", binding.tvPrinted.text.toString())
//        super.onSaveInstanceState(outState)
//    }
    fun setBundle():Fragment{
        val bundle = Bundle()
        bundle.putString("text", binding.tvPrinted.text.toString())
        Log.d("TAG", "setBundle: ${binding.tvPrinted.text}")
        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle
        parentFragmentManager.beginTransaction().replace(R.id.fl_main,SecondFragment()).commit()
        return secondFragment
    }
    fun getText():String{
        return binding.tvPrinted.text.toString()
    }
}