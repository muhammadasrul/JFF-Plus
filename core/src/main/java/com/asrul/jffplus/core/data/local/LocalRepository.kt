package com.asrul.jffplus.core.data.local

import com.asrul.jffplus.core.data.local.entity.MovieEntity
import com.asrul.jffplus.core.data.local.entity.TvShowEntity
import com.asrul.jffplus.core.data.local.room.RoomDao
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val roomDao: RoomDao) {

    fun getMovies(): Flowable<List<MovieEntity>> = roomDao.getMovie()

    fun getFavoriteMovies(): Flowable<List<MovieEntity>> = roomDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = roomDao.insertMovie(movies)

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.favorite = state
        roomDao.updateMovie(movie)
    }

    fun getTvShow(): Flowable<List<TvShowEntity>> = roomDao.getTvShow()

    fun getFavoriteTvShow(): Flowable<List<TvShowEntity>> = roomDao.getFavoriteTvShow()

    fun insertTvShow(tvShows: List<TvShowEntity>) = roomDao.insertTvShow(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.favorite = state
        roomDao.updateTvShow(tvShow)
    }
}