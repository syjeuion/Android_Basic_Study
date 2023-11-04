package com.example.navernewssearch.retrofit

import android.text.format.DateFormat
import com.google.gson.annotations.SerializedName
import retrofit2.http.Headers
import java.util.Date

data class News (
    @SerializedName("title")
    var title: String,

    @SerializedName("originallink")
    var originallink: String,

    @SerializedName("link")
    var link: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("pubDate")
    var pubDate: String
)

data class SearchResult(
    var items: List<News>
)