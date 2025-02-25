package com.br.films.ui.features.movies

import com.br.films.data.dto.MovieDto
import com.br.films.data.dto.MovieResponseDto
import com.br.films.data.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel
    private val repository: MovieRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun `fetchPopularMovies should update movies LiveData on success`() = runTest {
        val fakeMovies = listOf(MovieDto(1, "Dune", "/dune.jpg", "2024", "Descrição"))
        coEvery { repository.getPopularMovies() } returns Result.success(MovieResponseDto(fakeMovies))


        viewModel.sendAction(MoviesAction.LoadMovies)
        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals(fakeMovies, state.movieResponseDto.movies)

        coVerify(exactly = 1)  { repository.getPopularMovies() }
    }

    @Test
    fun `fetchPopularMovies should update error LiveData on failure`() = runTest {
        coEvery { repository.getPopularMovies() } returns Result.failure(Exception("Erro no servidor"))

        viewModel.sendAction(MoviesAction.LoadMovies)
        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals("Erro ao carregar filmes", state.error)

        coVerify(exactly = 1) { repository.getPopularMovies() }
    }
}