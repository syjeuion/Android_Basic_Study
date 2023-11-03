package com.example.navernewssearch.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitService {
//    @Headers({
//        "X-Naver-Client-Id: iCygq2YmF5YBPsZUO6sA",
//        "X-Naver-Client-Secret: i23SX54wGs"
//    })
    @GET("rss/channel/item")
    fun getItems(): Call<List<News>>
}