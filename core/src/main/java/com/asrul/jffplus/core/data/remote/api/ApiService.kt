package com.asrul.jffplus.core.data.remote.api

import com.asrul.jffplus.core.BuildConfig
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&sort_by=popularity.desc&with_original_language=ja")
    fun getMovie(): Flowable<MovieResponse>

    @GET("discover/tv?api_key=${BuildConfig.API_KEY}&sort_by=popularity.desc&with_original_language=ja")
    fun getTvShow(): Flowable<TvResponse>
}