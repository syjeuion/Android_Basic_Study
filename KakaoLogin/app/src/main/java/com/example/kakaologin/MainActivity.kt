package com.example.kakaologin

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kakaologin.databinding.ActivityMainBinding
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Log.d(TAG, "keyhash : ${Utility.getKeyHash(this)}")

        getUserInfo()

    }

    //유저 정보 가져오기
    private fun getUserInfo(){
        UserApiClient.instance.me{ user, error ->
            if(error != null){
                Log.e(TAG, "사용자 정보 요청 실패 $error", )
            }else if(user != null){
                Log.e(TAG, "사용자 정보 요청 성공: $user", )
                binding.tvUserName.text = user.kakaoAccount?.profile?.nickname
            }
        }
    }
}