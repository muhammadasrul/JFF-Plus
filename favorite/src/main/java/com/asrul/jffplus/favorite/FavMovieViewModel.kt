package com.asrul.jffplus.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.core.domain.usecase.DataUseCase

class FavMovieViewModel (dataUseCase: DataUseCase): ViewModel() {
    val favoriteMovie = LiveDataReactiveStreams.fromPublisher(dataUseCase.getFavoriteMovies())
}