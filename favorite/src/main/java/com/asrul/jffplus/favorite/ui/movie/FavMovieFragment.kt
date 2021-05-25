package com.asrul.jffplus.favorite.ui.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.core.ui.MovieAdapter
import com.asrul.jffplus.core.utils.ItemClickCallbackListener
import com.asrul.jffplus.core.utils.setGone
import com.asrul.jffplus.databinding.FragmentMovieBinding
import com.asrul.jffplus.di.FavoriteModuleDependencies
import com.asrul.jffplus.favorite.DaggerFavoriteComponent
import com.asrul.jffplus.favorite.ui.ViewModelFactory
import com.asrul.jffplus.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavMovieFragment : Fragment() {

    private val binding: FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavMovieViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()
        viewModel.favoriteMovie.observe(viewLifecycleOwner, { movie ->
            movieAdapter.setMovies(movie)
            binding.progressBar.setGone()
            if (movie.isNotEmpty()) binding.tvEmpty.setGone()
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