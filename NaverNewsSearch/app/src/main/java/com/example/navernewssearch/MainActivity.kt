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
    lateinit var launcher: ActivityResultLauncher<Intent>

    private lateinit var retrofitClient: Retrofit
    private lateinit var retrofitService: RetrofitService

    var newsList= listOf<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        retrofitClient = RetrofitClient.getInstance()
//        retrofitService = retrofitClient.create(RetrofitService::class.java)

        retrofitClient = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofitClient.create(RetrofitService::class.java)

        val TAG = "RETROFIT"

//        val newsList = arrayListOf<News>(
//            News("title1","description1","date1"),
//            News("title2","description2","date2"),
//            News("title3","description3","date3")
//        )
//        initializeViews(newsList)



        binding.btnLetsSearch.setOnClickListener{
            var searchWord = binding.etInputSearchWord.text.toString()
            val newsItemsCall = retrofitService.getSearchNews("fLsbsBXA5kgnhzYqAzGt","bk_GPdBIPz", searchWord)

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
//        binding.btnLetsSearch.setOnClickListener {
//            retrofitService.getItems().enqueue(object : Callback<List<News>>{
//                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
//                    Log.d(TAG, "onResponse: ${response.body()}")
//                    val res = response.body()
//                    binding.etInputSearchWord.text = null
//
//                    if(res!=null){
//                        for(data in response.body()!!){
//
//                        }
//                    }
//                }
//            })
//        }
    }

    //RecyclerView
    private fun initializeViews(newsList: List<News>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerAdapter(newsList)
    }

    //Retrofit
//    private var instance: Retrofit? = null
//    object RetrofitClient{
//
//        private val gson = GsonBuilder().setLenient().create()
//
//        fun getInstance(): Retrofit{
//            if(instance == null){
//                instance = Retrofit.Builder()
//                    .baseUrl("https://openapi.naver.com/v1/search/news.json")
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build()
//            }
//            return instance!!
//        }
//    }
}