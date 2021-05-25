package com.asrul.jffplus.core.data

import com.asrul.jffplus.core.data.local.LocalRepository
import com.asrul.jffplus.core.data.local.entity.MovieEntity
import com.asrul.jffplus.core.data.local.entity.TvShowEntity
import com.asrul.jffplus.core.data.remote.*
import com.asrul.jffplus.core.data.remote.api.*
import com.asrul.jffplus.core.domain.model.Data
import com.asrul.jffplus.core.domain.repository.IRepository
import com.asrul.jffplus.core.utils.AppExecutors
import com.asrul.jffplus.core.utils.Maps
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
): IRepository {

    override fun getMovie(): Flowable<Resource<List<Data>>> {

        return object: NetworkBoundResource<List<Data>, List<MovieItem>>() {
            override fun loadFromDb(): Flowable<List<Data>> {
                return localRepository.getMovies().map { Maps.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFatch(data: List<Data>): Boolean = data.isNullOrEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieItem>>> = remoteRepository.getMovie()

            override fun saveCallResult(data: List<MovieItem>) {
                val movie = mutableListOf<MovieEntity>()
                for (i in data.indices) {
                    movie.add(
                        MovieEntity(
                            data[i].id.toString(),
                            data[i].title,
                            data[i].releaseDate,
                            data[i].popularity.toString(),
                            data[i].voteCount.toString(),
                            data[i].posterPath,
                            data[i].backdropPath,
                            data[i].overview,
                            data[i].voteAverage
                        )
                    )
                }
                localRepository.insertMovies(movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowableData()
    }

    override fun getFavoriteMovies(): Flowable<List<Data>> {
        return localRepository.getFavoriteMovies().map { Maps.mapMovieEntitiesToDomain(it) }
    }

    override fun setFavoriteMovieState(movie: Data, state: Boolean) {
        val movieEntity = Maps.mapDomainToMovieEntity(movie)
        appExecutors.diskIO().execute {
            localRepository.setFavoriteMovie(movieEntity, state)
        }
    }

    override fun getTvShow(): Flowable<Resource<List<Data>>> {

        return object: NetworkBoundResource<List<Data>, List<TvItem>>() {
            override fun loadFromDb(): Flowable<List<Data>> {
                return localRepository.getTvShow().map { Maps.mapTvEntitiesToDomain(it) }
            }

            override fun shouldFatch(data: List<Data>): Boolean = data.isNullOrEmpty()

            override fun createCall(): Flowable<ApiResponse<List<TvItem>>> = remoteRepository.getTvShow()

            override fun saveCallResult(data: List<TvItem>) {
                val tvShow = mutableListOf<TvShowEntity>()
                for (i in data.indices) {
                    tvShow.add(
                        TvShowEntity(
                            data[i].id.toString(),
                            data[i].name,
                            data[i].firstAirDate,
                            data[i].popularity.toString(),
                            data[i].voteCount.toString(),
                            data[i].posterPath,
                            data[i].backdropPath,
                            data[i].overview,
                            data[i].voteAverage
                        )
                    )
                }
                localRepository.insertTvShow(tvShow)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowableData()
    }

    override fun getFavoriteTvShows(): Flowable<List<Data>> {
        return localRepository.getFavoriteTvShow().map { Maps.mapTvEntitiesToDomain(it) }
    }

    override fun setFavoriteTvState(tvShow: Data, state: Boolean) {
        val tvShowEntity = Maps.mapDomainToTvEntity(tvShow)
        appExecutors.diskIO().execute {
            localRepository.setFavoriteTvShow(tvShowEntity, state)
        }
    }
}