package com.asrul.jffplus.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.core.BuildConfig
import com.asrul.jffplus.core.R
import com.asrul.jffplus.core.databinding.RowItemBinding
import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.core.utils.ItemClickCallbackListener
import java.util.*

class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = ArrayList<Data>()
    private lateinit var itemClickCallbackListener: ItemClickCallbackListener

    fun setOnItemClickCallback(itemClickCallbackListener: ItemClickCallbackListener) {
        this.itemClickCallbackListener = itemClickCallbackListener
    }

    fun setMovies(movies: List<Data>?) {
        if (movies == null) return
            this.movieList.clear()
            this.movieList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = movieList[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Data) {
            with(binding) {
                tvTitle.text = movie.title
                tvYear.text = movie.releaseDate
                tvAbout.text = movie.overview
                val rating = movie.voteAverage.div(VOTE_AVERAGE_DIV)
                ratingBar.rating = rating.toFloat()

                imgPoster.load(BuildConfig.POSTER_URL + movie.posterPath) {
                    placeholder(R.drawable.ic_baseline_refresh_24)
                    error(R.drawable.ic_baseline_broken_image_24)
                    transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                }

                itemView.setOnClickListener {
                    itemClickCallbackListener.onItemClicked(movie)
                }
            }
        }
    }

    companion object {
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}