package com.asrul.jffplus.ui.tvshow

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
import com.asrul.jffplus.core.ui.TvShowAdapter
import com.asrul.jffplus.core.utils.ItemClickCallbackListener
import com.asrul.jffplus.databinding.FragmentTvShowBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val binding: FragmentTvShowBinding by lazy {
        FragmentTvShowBinding.inflate(layoutInflater)
    }

    private val viewModel: TvShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = TvShowAdapter()
        viewModel.tvShow.observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null) {
                when(tvShow) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        tvShowAdapter.setTvShow(tvShow.data)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                    }
                }
            }
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