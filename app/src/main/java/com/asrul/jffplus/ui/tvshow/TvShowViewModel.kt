package com.asrul.jffplus.ui.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.core.domain.usecase.DataUseCase

class TvShowViewModel @ViewModelInject constructor(dataUseCase: DataUseCase) : ViewModel() {
    val tvShow = LiveDataReactiveStreams.fromPublisher(dataUseCase.getTvShow())
}