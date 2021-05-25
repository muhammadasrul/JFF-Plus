package com.asrul.jffplus.core.data.remote

import android.annotation.SuppressLint
import android.util.Log
import com.asrul.jffplus.core.data.remote.api.ApiService
import com.asrul.jffplus.core.data.remote.api.MovieItem
import com.asrul.jffplus.core.data.remote.api.TvItem
import com.asrul.jffplus.core.utils.EspressoIdlingResource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getMovie(): Flowable<ApiResponse<List<MovieItem>>> {
        val movieData = PublishSubject.create<ApiResponse<List<MovieItem>>>()
        EspressoIdlingResource.increment()

        val client = apiService.getMovie()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val data = response.results
                movieData.onNext(if (data.isNotEmpty()) ApiResponse.Success(data) else ApiResponse.Empty)
            }, { error ->
                movieData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteRepository", error.message.toString())
            })

        return movieData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getTvShow(): Flowable<ApiResponse<List<TvItem>>> {
        val tvShowData = PublishSubject.create<ApiResponse<List<TvItem>>>()
        EspressoIdlingResource.increment()
        apiService.getTvShow()
            .subscribeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val data = response.results
                tvShowData.onNext(data.let { ApiResponse.Success(data) })

            }, { error ->
                Log.e("RemoteRepository", error.message.toString())
            })

        return tvShowData.toFlowable(BackpressureStrategy.BUFFER)
    }
}