package com.asrul.jffplus.core.data.local.room

import androidx.room.*
import com.asrul.jffplus.core.data.local.entity.MovieEntity
import com.asrul.jffplus.core.data.local.entity.TvShowEntity
import com.asrul.jffplus.core.utils.Maps
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface RoomDao {

    @Query("SELECT * FROM ${Maps.movieTable}")
    fun getMovie(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM ${Maps.movieTable} WHERE ${Maps.favorite} = 1")
    fun getFavoriteMovies(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(data: List<MovieEntity>): Completable

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMovie(data: MovieEntity)

    @Query("SELECT * FROM ${Maps.tvShowTable}")
    fun getTvShow(): Flowable<List<TvShowEntity>>

    @Query("SELECT * FROM ${Maps.tvShowTable} WHERE ${Maps.favorite} = 1")
    fun getFavoriteTvShow(): Flowable<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(data: List<TvShowEntity>): Completable

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateTvShow(data: TvShowEntity)
}