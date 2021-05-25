package com.asrul.jffplus.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asrul.jffplus.core.utils.SectionPagerAdapter
import com.asrul.jffplus.databinding.FragmentFavoriteBinding
import com.asrul.jffplus.ui.favorite.favmovie.FavMovieFragment
import com.asrul.jffplus.ui.favorite.favtvshow.FavTvShowFragment

class FavoriteFragment : Fragment() {

    private val binding: FragmentFavoriteBinding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionPagerAdapter.addFragment(FavMovieFragment())
        sectionPagerAdapter.addFragment(FavTvShowFragment())
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}