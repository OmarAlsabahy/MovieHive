package com.example.moviehive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.moviehive.Api.Models.MovieDetailsModel
import com.example.moviehive.Api.MovieApi
import com.example.moviehive.ViewModels.DetailsViewModel
import com.example.moviehive.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    lateinit var binding : FragmentDetailsBinding
    var movieId:Int = 0
    val viewModel : DetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetailsBinding.bind(view)

        movieId = DetailsFragmentArgs.fromBundle(requireArguments()).movieId
        viewModel.getMovieById(movieId)
        observe()
        binding.btnPlay.setOnClickListener {
            onClick(movieId)
        }

    }

    private fun onClick(movieId: Int) {
        findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToTrailerFragment(movieId))
    }

    private fun observe() {
        viewModel.movie.observe(viewLifecycleOwner){
            bind(it)
        }
    }

    private fun bind(movieDetailsModel: MovieDetailsModel) {
        binding.movieName.text = movieDetailsModel.original_title
        binding.description.text = movieDetailsModel.overview
        binding.rate.text = String.format("%.1f",movieDetailsModel.vote_average)
        Glide.with(binding.root)
            .load("https://image.tmdb.org/t/p/w500"+movieDetailsModel.poster_path)
            .into(binding.posterImage)
    }
}