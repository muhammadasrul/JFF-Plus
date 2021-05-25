package com.asrul.jffplus.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asrul.jffplus.core.domain.usecase.DataUseCase
import com.asrul.jffplus.favorite.ui.movie.FavMovieViewModel
import com.asrul.jffplus.favorite.ui.tvshow.FavTvShowViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val dataUseCase: DataUseCase): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                FavMovieViewModel(dataUseCase) as T
            }
            modelClass.isAssignableFrom(FavTvShowViewModel::class.java) -> {
                FavTvShowViewModel(dataUseCase) as T
            }
            else -> throw Throwable("unknown model class ${modelClass.name}")
        }
}