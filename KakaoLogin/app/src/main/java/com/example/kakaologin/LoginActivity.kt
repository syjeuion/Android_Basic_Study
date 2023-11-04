package com.example.kakaologin

import android.app.Application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kakaologin.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    val TAG = "login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKakaoLogin.setOnClickListener {
            kakaoLogin()
            checkToken()
        }
    }

    //이메일 로그인 콜백
    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if(error != null){
            Log.e(TAG, "로그인 실패: $error" )
        }else if(token != null){
            Log.e(TAG, "로그인 성공: ${token.accessToken}" )
            nextMainActivity()
        }
    }

    //로그인
    private fun kakaoLogin(){
        //카카오톡 설치 확인
        if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
            //카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(this){ token, error ->
                if(error != null){
                    Log.e(TAG, "로그인 실패 : $error")
                    //사용자가 취소
                    if(error is ClientError && error.reason == ClientErrorCause.Cancelled){
                        return@loginWithKakaoTalk
                    }
                    //다른 오류
                    else{
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) //카카오 이메일 로그인
                    }
                }
                //로그인 성공
                else if (token != null){
                    Log.e(TAG, "로그인 성공: ${token.accessToken}", )
                    nextMainActivity()
                }
            }
        }else{
            UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) //카카오 이메일 로그인
        }
    }

    //토큰 만료 체크
    private fun checkToken(){
        if(AuthApiClient.instance.hasToken()){
            UserApiClient.instance.accessTokenInfo{ _, error ->
                if(error != null){
                    if(error is KakaoSdkError && error.isInvalidTokenError()){
                        //로그인 필요
                        kakaoLogin()
                    }else{
                        //기타 에러
                    }
                }else{
                    //토큰 유효성 체크 성공 (필요시 토큰 갱신)
                }
            }
        }else{
            //로그인 필요
            kakaoLogin()
        }
    }

    private fun nextMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "98ee993a73331460a9f955224bdd1c67")
    }
}