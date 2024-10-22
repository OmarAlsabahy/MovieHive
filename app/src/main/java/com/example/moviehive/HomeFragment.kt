package com.example.moviehive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviehive.RecyclerViewAdapters.NowShowingAdapter
import com.example.moviehive.RecyclerViewAdapters.PopularAdapter
import com.example.moviehive.ViewModels.HomeViewModel
import com.example.moviehive.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()
    lateinit var nowShowingAdapter : NowShowingAdapter
    lateinit var popularAdapter : PopularAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        viewModel.getNowShowing()
        viewModel.getPopular()
        viewModel.showingMovies.observe(viewLifecycleOwner){
            nowShowingAdapter = NowShowingAdapter(it)
            binding.nowShowingRecycler.adapter = nowShowingAdapter
        }
        viewModel.popularMovies.observe(viewLifecycleOwner){
            popularAdapter = PopularAdapter(it)
            binding.popularRecycler.adapter = popularAdapter
        }
    }
}