package com.asrul.jffplus.core.domain.usecase

import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.core.domain.repository.IRepository
import javax.inject.Inject

class DataInteractor @Inject constructor(private val repository: IRepository): DataUseCase {
    override fun getMovie() = repository.getMovie()

    override fun getFavoriteMovies() = repository.getFavoriteMovies()

    override fun setFavoriteMovieState(movie: Data, state: Boolean) = repository.setFavoriteMovieState(movie, state)

    override fun getTvShow() = repository.getTvShow()

    override fun getFavoriteTvShows() = repository.getFavoriteTvShows()

    override fun setFavoriteTvState(tvShow: Data, state: Boolean) = repository.setFavoriteTvState(tvShow, state)
}