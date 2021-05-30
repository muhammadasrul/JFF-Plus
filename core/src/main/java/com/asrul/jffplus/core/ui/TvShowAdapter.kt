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

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<Data>()
    private lateinit var itemClickCallbackListener: ItemClickCallbackListener

    fun setOnItemClickCallback(itemClickCallbackListener: ItemClickCallbackListener) {
        this.itemClickCallbackListener = itemClickCallbackListener
    }

    fun setTvShow(tvShows: List<Data>?) {
        if (tvShows == null) return
            this.listTvShow.clear()
            this.listTvShow.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShows = listTvShow[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: Data) {
            with(binding) {
                tvTitle.text = tvShow.title
                tvYear.text = tvShow.releaseDate
                tvAbout.text = tvShow.overview
                val rating = tvShow.voteAverage.div(VOTE_AVERAGE_DIV)
                ratingBar.rating = rating.toFloat()

                imgPoster.load(BuildConfig.POSTER_URL+tvShow.posterPath) {
                    placeholder(R.drawable.ic_baseline_refresh_24)
                    error(R.drawable.ic_baseline_broken_image_24)
                    transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                }

                itemView.setOnClickListener {
                    itemClickCallbackListener.onItemClicked(tvShow)
                }
            }
        }
    }

    companion object {
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}