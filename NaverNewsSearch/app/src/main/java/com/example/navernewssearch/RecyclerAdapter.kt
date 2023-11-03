package com.example.navernewssearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navernewssearch.databinding.ItemNewsBinding
import com.example.navernewssearch.retrofit.News

class RecyclerAdapter(val itemList: List<News>):RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder{
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(news:News){
            binding.tvTitle.text = news.title
            binding.tvDescription.text = news.description
            binding.tvDate.text = news.pubDate.toString()
        }
    }
}