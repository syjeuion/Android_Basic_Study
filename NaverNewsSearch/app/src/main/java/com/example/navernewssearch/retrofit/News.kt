package com.example.navernewssearch.retrofit

import android.text.format.DateFormat
import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("pubDate")
    val pubDate: String
)