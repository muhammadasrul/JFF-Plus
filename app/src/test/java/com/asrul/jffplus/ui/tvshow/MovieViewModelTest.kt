package com.asrul.jffplus.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.asrul.jffplus.core.data.Repository
import com.asrul.jffplus.core.data.local.entity.MovieEntity
import com.asrul.jffplus.core.data.Resource
import com.asrul.jffplus.ui.movie.MovieViewModel
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovie() {
        val dummy = Resource.success(listOf<MovieEntity>())
        val data = MutableLiveData<Resource<List<MovieEntity>>>()
        data.value = dummy

        `when`(repository.getMovie()).thenReturn(data)
        viewModel.getMovie().observeForever(observer)
        verify(repository).getMovie()
        verify(observer).onChanged(dummy)
        assertNotNull(data)
    }
}