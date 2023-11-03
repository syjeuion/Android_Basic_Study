package com.example.navernewssearch.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("items")
    fun getItems(): Call<News>
}