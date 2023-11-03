package com.example.navernewssearch.retrofit

import android.text.format.DateFormat
import com.google.gson.annotations.SerializedName
import retrofit2.http.Headers
import java.util.Date

data class News (
    @SerializedName("title")
    val title: String,

    @SerializedName("originallink")
    val originallink: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("pubDate")
    val pubDate: Date
)