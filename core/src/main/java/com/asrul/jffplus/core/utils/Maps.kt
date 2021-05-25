package com.asrul.jffplus.core.utils

import com.asrul.jffplus.core.data.local.entity.MovieEntity
import com.asrul.jffplus.core.data.local.entity.TvShowEntity
import com.asrul.jffplus.core.domain.model.Data

object Maps {
    const val id = "id"
    const val posterPath = "poster_path"
    const val backdropPath = "backdrop_path"
    const val overview = "overview"
    const val voteAverage = "vote_average"
    const val favorite = "favorite"
    const val popularity = "popularity"
    const val title = "title"
    const val release = "release_date"
    const val voteCount = "vote_count"

    const val movieTable = "movies"
    const val tvShowTable = "tv_show"

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Data> =
        input.map {
            Data(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteCount = it.voteCount,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                voteAverage = it.voteAverage,
                favorite = it.favorite,
            )
        }

    fun mapDomainToMovieEntity(input: Data) = MovieEntity(
        id = input.id,
        title = input.title,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteCount = input.voteCount,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        overview = input.overview,
        voteAverage = input.voteAverage,
        favorite = input.favorite,
    )

    fun mapTvEntitiesToDomain(input: List<TvShowEntity>): List<Data> =
        input.map {
            Data(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteCount = it.voteCount,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                voteAverage = it.voteAverage,
                favorite = it.favorite,
            )
        }

    fun mapDomainToTvEntity(input: Data) = TvShowEntity(
        id = input.id,
        title = input.title,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteCount = input.voteCount,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        overview = input.overview,
        voteAverage = input.voteAverage,
        favorite = input.favorite,
    )
}