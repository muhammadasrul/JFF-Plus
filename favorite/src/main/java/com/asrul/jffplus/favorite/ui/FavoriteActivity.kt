package com.asrul.jffplus.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asrul.jffplus.core.utils.SectionPagerAdapter
import com.asrul.jffplus.favorite.databinding.ActivityFavoriteBinding
import com.asrul.jffplus.favorite.ui.movie.FavMovieFragment
import com.asrul.jffplus.favorite.ui.tvshow.FavTvShowFragment

class FavoriteActivity : AppCompatActivity() {

    private val binding: ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        sectionPagerAdapter.addFragment(FavMovieFragment())
        sectionPagerAdapter.addFragment(FavTvShowFragment())

        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}