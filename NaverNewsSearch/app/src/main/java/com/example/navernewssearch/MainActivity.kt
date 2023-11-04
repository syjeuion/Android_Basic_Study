package com.example.navernewssearch

import android.content.ClipData.Item
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navernewssearch.databinding.ActivityMainBinding
import com.example.navernewssearch.retrofit.News
import com.example.navernewssearch.retrofit.RetrofitService
import com.example.navernewssearch.retrofit.SearchResult
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    lateinit var launcher: ActivityResultLauncher<Intent>

    private lateinit var retrofitClient: Retrofit
    private lateinit var retrofitService: RetrofitService

    var newsList= listOf<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitClient = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofitClient.create(RetrofitService::class.java)

        val TAG = "RETROFIT"
        val CLIENT_ID = BuildConfig.NAVER_CLIENT_ID
        val CLIENT_SECRET = BuildConfig.NAVER_CLIENT_SECRET

        binding.btnLetsSearch.setOnClickListener{
            var searchWord = binding.etInputSearchWord.text.toString()
            binding.etInputSearchWord.text = null

            val newsItemsCall = retrofitService.getSearchNews(CLIENT_ID,CLIENT_SECRET, searchWord)

            newsItemsCall.enqueue(object: Callback<SearchResult>{
                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    if(response.isSuccessful){
                        //성공
                        val itemList = response.body()
                        Log.d(TAG, "onResponse: ${itemList}")
                        if (itemList != null) {
                            newsList = itemList.items
                        }
                        initializeViews(newsList)
                        Log.d(TAG, "Success")
                    }else{
                        //실패
                        Log.d(TAG, "Fail")
                    }
                }
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    //통신 실패
                    Log.d(TAG, "onFailure")
                    call.cancel()
                }
            })
        }
    }

    //RecyclerView
    private fun initializeViews(newsList: List<News>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerAdapter(newsList)
    }

}