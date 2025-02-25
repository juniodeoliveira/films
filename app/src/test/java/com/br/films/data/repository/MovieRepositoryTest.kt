package com.br.films.data.repository

import com.br.films.data.api.MovieApiService
import com.br.films.data.model.Movie
import com.br.films.data.model.MovieResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class MovieRepositoryTest {
    private val apiService = mock<MovieApiService>()
    private val repository = MovieRepositoryImpl(apiService)

    @Test
    fun `getPopularMovies should return successful data`() = runBlocking {
        val fakeResponse = MovieResponse(
            movies = listOf(
                Movie(
                    1,
                    "Dune",
                    "/dune.jpg",
                    "2024-03-01",
                    "A guerra do deserto."
                )
            )
        )

        `when`(apiService.getPopularMovies()).thenReturn(fakeResponse)

        val result = repository.getPopularMovies()

        assert(result.isSuccess)
        assertEquals(1, result.getOrNull()?.movies?.size)
        assertEquals("Dune", result.getOrNull()?.movies?.first()?.title)
    }

    @Test
    fun `getPopularMovies should return failure when API fails`() = runBlocking {
        `when`(apiService.getPopularMovies()).thenThrow(RuntimeException("API error"))

        val result = repository.getPopularMovies()

        assert(result.isFailure)
        assertEquals("API error", result.exceptionOrNull()?.message)
    }
}
