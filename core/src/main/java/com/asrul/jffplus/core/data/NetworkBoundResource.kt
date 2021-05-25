package com.asrul.jffplus.core.data

import com.asrul.jffplus.core.data.remote.ApiResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = PublishSubject.create<Resource<ResultType>>()
    private val mCompositeDisposable = CompositeDisposable()

    init {
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        val db = dbSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { value ->
                dbSource.unsubscribeOn(Schedulers.io())
                if (shouldFatch(value)) {
                    fetchFromNetwork()
                } else {
                    result.onNext(
                        Resource.Success(
                            value
                        )
                    )
                }
            }
        mCompositeDisposable.add(db)
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDb(): Flowable<ResultType>

    protected abstract fun shouldFatch(data: ResultType): Boolean

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork() {

        val apiResponse = createCall()

        result.onNext(Resource.Loading(null))

        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        saveCallResult(response.data)
                        val dbSource = loadFromDb()
                        dbSource.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(
                                    Resource.Success(
                                        it
                                    )
                                )
                            }
                    }
                    is ApiResponse.Empty -> {
                        val dbSource = loadFromDb()

                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(
                                    Resource.Success(
                                        it
                                    )
                                )
                            }
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        result.onNext(
                            Resource.Error(
                                response.errorMessage,
                                null
                            )
                        )
                    }
                }
            }

        mCompositeDisposable.add(response)
    }

    fun asFlowableData(): Flowable<Resource<ResultType>> =
        result.toFlowable(BackpressureStrategy.BUFFER)
}