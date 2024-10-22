package com.example.moviehive.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviehive.Api.Models.MovieResult
import com.example.moviehive.databinding.PopularMoviesItemBinding

class PopularAdapter(private val data : List<MovieResult>):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularMoviesItemBinding.inflate(LayoutInflater.from(parent.context),parent , false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(data[position])
    }


    inner class ViewHolder(private val binding : PopularMoviesItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(nowShowingResult: MovieResult) {
            binding.movieName.text = nowShowingResult.original_title
            binding.rate.text = nowShowingResult.vote_average.toString()
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500"+nowShowingResult.poster_path)
                .into(binding.postImage)
        }

    }


}