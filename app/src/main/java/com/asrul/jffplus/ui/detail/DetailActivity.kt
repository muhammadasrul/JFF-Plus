package com.asrul.jffplus.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by viewModels()

    private var movieDetail: Data? = null
    private var tvShowDetail: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWindow()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        movieDetail = intent.getParcelableExtra(EXTRA_MOVIE)
        tvShowDetail = intent.getParcelableExtra(EXTRA_TV_SHOW)

        bindMovie()
        bindTvShow()
    }

    private fun initWindow() {
        val w: Window = window
        w.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun bindTvShow() {
        tvShowDetail?.let {
            viewModel.tvShowDetail.value = tvShowDetail
            viewModel.tvShowDetail.observe(this, { tvShow ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    imagePoster.load(BuildConfig.POSTER_URL+tvShow.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                        transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                    }
                    imageBackdrop.load(BuildConfig.POSTER_URL+tvShow.backdropPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                    }
                    textTitle.text = tvShow.title
                    textRelease.text = tvShow.releaseDate
                    textPopularity.text = tvShow.popularity
                    textVcount.text = tvShow.voteCount
                    textAbout.text = tvShow.overview
                    val rating = tvShow.voteAverage.div(VOTE_AVERAGE_DIV)
                    ratingBar.rating = rating.toFloat()
                    if (tvShow.favorite) {
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                    } else {
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }
                }
            })
            binding.apply {
                btnFav.setOnClickListener {
                    if (tvShowDetail?.favorite == true) {
                        tvShowDetail?.let { viewModel.setTvShowFavoriteState(it, false) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        Toast.makeText(baseContext, resources.getString(R.string.remove_from_favorite), Toast.LENGTH_SHORT).show()
                    } else {
                        tvShowDetail?.let { viewModel.setTvShowFavoriteState(it, true) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                        Toast.makeText(baseContext, resources.getString(R.string.add_to_favorite), Toast.LENGTH_SHORT).show()
                    }
                }

                if (tvShowDetail?.favorite == true) {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }

    private fun bindMovie() {
        movieDetail?.let {
            viewModel.movieDetail.value = movieDetail
            viewModel.movieDetail.observe(this, { movie ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    imagePoster.load(BuildConfig.POSTER_URL+movieDetail?.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                        transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                    }

                    imageBackdrop.load(BuildConfig.POSTER_URL+movieDetail?.backdropPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)

                    }

                    textTitle.text = movie.title
                    textRelease.text = movie.releaseDate
                    textPopularity.text = movie.popularity
                    textVcount.text = movie.voteCount
                    textAbout.text = movie.overview
                    val rating = movie.voteAverage.div(VOTE_AVERAGE_DIV)
                    ratingBar.rating = rating.toFloat()
                }
            })

            binding.apply {
                btnFav.setOnClickListener {
                    if (movieDetail?.favorite == true) {
                        movieDetail?.let { viewModel.setMovieFavoriteState(it, false) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        Toast.makeText(baseContext, resources.getString(R.string.remove_from_favorite), Toast.LENGTH_SHORT).show()
                    } else {
                        movieDetail?.let { viewModel.setMovieFavoriteState(it, true) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                        Toast.makeText(baseContext, resources.getString(R.string.add_to_favorite), Toast.LENGTH_SHORT).show()
                    }
                }

                if (movieDetail?.favorite == true) {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}