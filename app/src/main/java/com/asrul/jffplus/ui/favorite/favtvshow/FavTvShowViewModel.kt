package com.asrul.jffplus.ui.favorite.favtvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.core.domain.usecase.DataUseCase

class FavTvShowViewModel @ViewModelInject constructor(dataUseCase: DataUseCase): ViewModel() {
    val favoriteTvShow = LiveDataReactiveStreams.fromPublisher(dataUseCase.getFavoriteTvShows())
}