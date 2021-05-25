package com.asrul.jffplus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.asrul.jffplus.R
import com.asrul.jffplus.databinding.CarouselLayoutBinding
import com.asrul.jffplus.databinding.FragmentHomeBinding
import com.asrul.jffplus.ui.movie.MovieFragment
import com.asrul.jffplus.ui.tvshow.TvShowFragment
import com.asrul.jffplus.core.utils.SectionPagerAdapter
import com.synnapps.carouselview.ViewListener

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
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

        initToolbar()
        binding.apply {
            carouselView.setViewListener(viewListener)
            carouselView.pageCount = carouselImg.size

            sectionPagerAdapter.addFragment(MovieFragment())
            sectionPagerAdapter.addFragment(TvShowFragment())

            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

    }

    private val carouselImg = intArrayOf(
        R.drawable.b_1,
        R.drawable.b_2,
        R.drawable.b_3
    )

    private val carouselTxt = arrayOf(
        "Talks & Event",
        "Film LineUp",
        "JFF Plus: Online Festival"
    )

    private val carouselDcs = arrayOf(
        "Special content of JFF Plus: Online Festival",
        "From the latest trending films to classic cinema, explore our special selection of the Japan’s acclaimed films.",
        "Join JFF PLus and enjoy Japanese films while the feastival goes on!"
    )

    private val viewListener = ViewListener {position ->
        val customBinding = CarouselLayoutBinding.inflate(LayoutInflater.from(context))

        with(customBinding) {
            imgCarousel.load(carouselImg[position]) {
                placeholder(R.drawable.ic_baseline_refresh_24)
                error(R.drawable.ic_baseline_broken_image_24)
            }
            txtCarousel.text = carouselTxt[position]
            txtCarouselDesc.text = carouselDcs[position]
        }

        return@ViewListener customBinding.root
    }

    private fun initToolbar() {
        binding.collapsingToolbar.apply {
            title = getString(R.string.app_full_name)
            setCollapsedTitleTextColor(ContextCompat.getColor(context, R.color.textColor))
            setExpandedTitleColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
    }
}