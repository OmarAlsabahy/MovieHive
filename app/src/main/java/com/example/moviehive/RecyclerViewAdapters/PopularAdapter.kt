package com.example.moviehive.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviehive.Api.Models.MovieResult
import com.example.moviehive.ClickListnersInterfaces.MovieClickListener
import com.example.moviehive.databinding.PopularMoviesItemBinding

class PopularAdapter( private val listener: MovieClickListener):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private var data : MutableList<MovieResult> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularMoviesItemBinding.inflate(LayoutInflater.from(parent.context),parent , false)
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

    fun addMovies(movies:List<MovieResult>){
        val previousSize = data.size
        data.addAll(movies)
        notifyItemRangeInserted(previousSize , movies.size)
    }


    inner class ViewHolder(private val binding : PopularMoviesItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(popularResult: MovieResult) {
            binding.movieName.text = popularResult.original_title
            binding.rate.text = String.format("%.1f",popularResult.vote_average)
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500"+popularResult.poster_path)
                .into(binding.postImage)
        }

        fun onClick(movieResult: MovieResult) {
            listener.onClick(movieResult.id)
        }

    }


}