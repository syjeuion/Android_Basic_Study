package com.example.study01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.study01.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var launcher: ActivityResultLauncher<Intent>

    lateinit var firstFragment:FirstFragment
    lateinit var secondFragment:SecondFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstFragment = FirstFragment()
        secondFragment = SecondFragment()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                val editText = result.data?.getStringExtra("editText").toString()
                val firstFragment =supportFragmentManager.findFragmentById(R.id.fl_main) as FirstFragment
                firstFragment.setText(editText)
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.fl_main,FirstFragment()).commit()

        binding.bnvGnb.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Frag1->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,FirstFragment()).commit()
                }
                R.id.Frag2->{
//                    val firstFragment = supportFragmentManager.findFragmentById(R.id.fl_main) as FirstFragment
                    val bundle = Bundle()
                    bundle.putString("textFromFirst",firstFragment.getText())
                    secondFragment.arguments= bundle

                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,secondFragment).commit()
                }
                R.id.Frag3->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,ThirdFragment()).commit()
                }
            }
            true
        }


    }

    fun goSubActivity(text:String){
        val intent = Intent(this,SubActivity::class.java)
        intent.putExtra("originalText", text)
        launcher.launch(intent)
    }
}