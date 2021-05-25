package com.asrul.jffplus.favorite

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
import com.asrul.jffplus.core.ui.TvShowAdapter
import com.asrul.jffplus.core.utils.ItemClickCallbackListener
import com.asrul.jffplus.databinding.FragmentTvShowBinding
import com.asrul.jffplus.di.FavoriteModuleDependencies
import com.asrul.jffplus.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavTvShowFragment : Fragment() {

    private val binding: FragmentTvShowBinding by lazy {
        FragmentTvShowBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavTvShowViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = TvShowAdapter()
        viewModel.favoriteTvShow.observe(viewLifecycleOwner, { tvShow ->
            tvShowAdapter.setTvShow(tvShow)
            binding.progressBar.visibility = if (tvShow.isNotEmpty()) View.GONE else View.VISIBLE
        })

        binding.rvTvShow.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter

            tvShowAdapter.setOnItemClickCallback(object: ItemClickCallbackListener {
                override fun onItemClicked(data: Data) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV_SHOW, data)
                    startActivity(intent)
                }
            })
        }
    }
}