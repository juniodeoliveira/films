package com.br.films.data.model

import com.br.films.common.getPosterUrl
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieResponseTest {
    @Test
    fun `convert MovieResponse to MovieResponseDto correctly`() {
        val movie = Movie(
            id = 1,
            title = "Oppenheimer",
            posterPath = "/oppenheimer.jpg",
            releaseDate = "2023-07-21",
            description = "Um filme sobre a criação da bomba atômica"
        )

        val listMovie = MovieResponse(listOf(movie))

        val dto = listMovie.toDto()

        assertEquals(movie.id, dto.movies[0].id)
        assertEquals(movie.title, dto.movies[0].title)
        assertEquals(movie.getPosterUrl(), dto.movies[0].imageUrl)
        assertEquals(movie.releaseDate, dto.movies[0].releaseDate)
        assertEquals(movie.description, dto.movies[0].description)
    }
}