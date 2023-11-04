package com.example.navernewssearch.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    @GET("v1/search/news.json")
    fun getSearchNews(
        @Header("X-Naver-Client-Id") ClientId:String,
        @Header("X-Naver-Client-Secret") ClientSecret:String,
        @Query("query") query:String
    ): Call<SearchResult>

}