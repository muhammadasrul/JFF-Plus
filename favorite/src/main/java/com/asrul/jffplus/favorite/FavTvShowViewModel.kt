package com.asrul.jffplus.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.core.domain.usecase.DataUseCase

class FavTvShowViewModel (dataUseCase: DataUseCase): ViewModel() {
    val favoriteTvShow = LiveDataReactiveStreams.fromPublisher(dataUseCase.getFavoriteTvShows())
}