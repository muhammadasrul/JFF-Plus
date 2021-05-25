package com.asrul.jffplus.ui.favorite.favmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.core.domain.usecase.DataUseCase

class FavMovieViewModel @ViewModelInject constructor(dataUseCase: DataUseCase): ViewModel() {
    val favoriteMovie = LiveDataReactiveStreams.fromPublisher(dataUseCase.getFavoriteMovies())
}