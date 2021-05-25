package com.asrul.jffplus.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.core.domain.usecase.DataUseCase

class DetailViewModel @ViewModelInject constructor(private val dataUseCase: DataUseCase): ViewModel() {
    var movieDetail = MutableLiveData<Data>()
    var tvShowDetail = MutableLiveData<Data>()

    fun setMovieFavoriteState(movie: Data, state: Boolean) {
        dataUseCase.setFavoriteMovieState(movie, state)
    }

    fun setTvShowFavoriteState(tvShow: Data, state: Boolean) {
        dataUseCase.setFavoriteTvState(tvShow, state)
    }
}