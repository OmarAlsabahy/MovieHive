package com.example.moviehive.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviehive.Api.Models.MovieResult
import com.example.moviehive.ClickListnersInterfaces.MovieClickListener
import com.example.moviehive.databinding.NowShowingItemBinding

class NowShowingAdapter(private val data : List<MovieResult> , private val listener: MovieClickListener):RecyclerView.Adapter<NowShowingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NowShowingItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            holder.onClick(data[position])
        }
    }

    inner class ViewHolder(private val binding : NowShowingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(nowShowingResult: MovieResult) {
            binding.movieName.text = nowShowingResult.original_title
            binding.rate.text = String.format("%.1f",nowShowingResult.vote_average)
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500"+nowShowingResult.poster_path)
                .into(binding.postImage)
        }

        fun onClick(movieResult: MovieResult) {
            listener.onClick(movieResult.id)
        }

    }
}