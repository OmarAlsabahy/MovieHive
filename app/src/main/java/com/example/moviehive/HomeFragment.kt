package com.example.moviehive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviehive.Api.Models.MovieResult
import com.example.moviehive.ClickListnersInterfaces.MovieClickListener
import com.example.moviehive.RecyclerViewAdapters.NowShowingAdapter
import com.example.moviehive.RecyclerViewAdapters.PopularAdapter
import com.example.moviehive.ViewModels.HomeViewModel
import com.example.moviehive.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() , MovieClickListener {
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
        observe()
        paginatePage()
        onBackPressed()
        nowShowingAdapter = NowShowingAdapter(this)
        popularAdapter = PopularAdapter(this)
        binding.popularRecycler.adapter = popularAdapter
        binding.nowShowingRecycler.adapter = nowShowingAdapter
    }




    /////////////////////////////////////////////////////////



    private fun onBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,onBackPressedCallback)
    }

    private fun paginatePage() {

        binding.popularRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val layout = recyclerView.layoutManager as LinearLayoutManager
                if (layout.findLastVisibleItemPosition() == popularAdapter.itemCount-1){
                    viewModel.increasePopularPageNumber()
                    viewModel.getPopular()
                }
            }
        })

        binding.nowShowingRecycler.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val layout = recyclerView.layoutManager as LinearLayoutManager
                if (layout.findLastVisibleItemPosition() == nowShowingAdapter.itemCount-1){
                    viewModel.increaseNowShowingPageNumber()
                    viewModel.getNowShowing()
                }
            }
        })
    }

    private fun observe() {
        viewModel.showingMovies.observe(viewLifecycleOwner){
                nowShowingAdapter.addMovies(it as MutableList)
        }

        viewModel.popularMovies.observe(viewLifecycleOwner){
                popularAdapter.addMovies(it as MutableList)
        }
    }

    override fun onClick(movieId: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
    }
}