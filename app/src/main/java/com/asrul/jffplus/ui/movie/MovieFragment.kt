package com.asrul.jffplus.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.R
import com.asrul.jffplus.core.data.Resource
import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.core.ui.MovieAdapter
import com.asrul.jffplus.core.utils.ItemClickCallbackListener
import com.asrul.jffplus.core.utils.setGone
import com.asrul.jffplus.core.utils.setVisible
import com.asrul.jffplus.databinding.FragmentMovieBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val binding: FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()
        viewModel.movie.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.setVisible()
                    is Resource.Success -> {
                        binding.progressBar.setGone()
                        binding.tvEmpty.setGone()
                        movieAdapter.setMovies(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.setGone()
                        Toast.makeText(requireContext(), getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter

            movieAdapter.setOnItemClickCallback(object : ItemClickCallbackListener {
                override fun onItemClicked(data: Data) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, data)
                    startActivity(intent)
                }
            })
        }
    }
}