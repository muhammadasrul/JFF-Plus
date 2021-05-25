package com.asrul.jffplus.core.domain.repository

import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.core.data.Resource
import io.reactivex.Flowable

interface IRepository {
    fun getMovie(): Flowable<Resource<List<Data>>>
    fun getFavoriteMovies(): Flowable<List<Data>>
    fun setFavoriteMovieState(movie: Data, state: Boolean)

    fun getTvShow(): Flowable<Resource<List<Data>>>
    fun getFavoriteTvShows(): Flowable<List<Data>>
    fun setFavoriteTvState(tvShow: Data, state: Boolean)
}