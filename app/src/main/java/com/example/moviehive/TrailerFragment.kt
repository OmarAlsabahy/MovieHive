package com.example.moviehive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviehive.ViewModels.TrailerViewModel
import com.example.moviehive.databinding.FragmentTrailerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailerFragment : Fragment() {
    lateinit var binding:FragmentTrailerBinding
    var movieId:Int = 0
    val viewModel : TrailerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trailer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTrailerBinding.bind(view)
        val youTubePlayer = binding.videoPlayer
        lifecycle.addObserver(youTubePlayer)
        movieId = TrailerFragmentArgs.fromBundle(requireArguments()).movieId
        youTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                    viewModel.getMovieKey(movieId)
                viewModel.movieKey.observe(viewLifecycleOwner){
                    if (!it.isNullOrEmpty()){
                        try {
                            youTubePlayer.loadVideo(it,0f)
                        }catch (e:Exception){
                            binding.warning.visible()
                            binding.videoPlayer.gone()
                        }
                    }else{
                        youTubePlayer.loadVideo("b_e_y_3HAs",0f)
                    }


                }

            }
        })

    }
}