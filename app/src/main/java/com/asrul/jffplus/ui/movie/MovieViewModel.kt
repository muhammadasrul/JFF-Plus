package com.asrul.jffplus.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.core.domain.usecase.DataUseCase
import javax.inject.Inject

class MovieViewModel @ViewModelInject constructor(dataUseCase: DataUseCase) : ViewModel() {
    val movie = LiveDataReactiveStreams.fromPublisher(dataUseCase.getMovie())
}