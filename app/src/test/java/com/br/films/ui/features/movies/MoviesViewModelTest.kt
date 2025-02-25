package com.br.films.ui.features.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.br.films.data.dto.MovieDto
import com.br.films.data.dto.MovieResponseDto
import com.br.films.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mock<MovieRepository>()
    private lateinit var viewModel: MoviesViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MoviesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchPopularMovies should update movies LiveData on success`() = runTest {
        val fakeMovies = listOf(MovieDto(1, "Dune", "/dune.jpg", "2024", "Descrição"))
        `when`(repository.getPopularMovies()).thenReturn(Result.success(MovieResponseDto(fakeMovies)))

        val moviesObserver = mock<Observer<List<MovieDto>>>()
        val loadingObserver = mock<Observer<Boolean>>()
        val errorObserver = mock<Observer<String?>>()

        viewModel.movies.observeForever(moviesObserver)
        viewModel.isLoading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)

        viewModel.fetchPopularMovies()
        advanceUntilIdle()

        verify(loadingObserver).onChanged(true)
        verify(loadingObserver).onChanged(false)

        verify(moviesObserver).onChanged(fakeMovies)

        verify(errorObserver).onChanged(null)
    }

    @Test
    fun `fetchPopularMovies should update error LiveData on failure`() = runTest {
        val errorMessage = "Erro na API"
        `when`(repository.getPopularMovies()).thenReturn(Result.failure(Exception(errorMessage)))

        val moviesObserver = mock<Observer<List<MovieDto>>>()
        val loadingObserver = mock<Observer<Boolean>>()
        val errorObserver = mock<Observer<String?>>()

        viewModel.movies.observeForever(moviesObserver)
        viewModel.isLoading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)

        viewModel.fetchPopularMovies()
        advanceUntilIdle()

        verify(loadingObserver).onChanged(true)
        verify(loadingObserver).onChanged(false)

        verify(moviesObserver, never()).onChanged(anyOrNull())

        verify(errorObserver).onChanged(errorMessage)
    }
}