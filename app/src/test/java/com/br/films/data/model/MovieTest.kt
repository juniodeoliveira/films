package com.br.films.data.model

import com.br.films.common.getPosterUrl
import org.junit.Assert.assertEquals
import org.junit.Test


class MovieTest {

    @Test
    fun `convert Movie to MovieDto correctly`() {
        val movie = Movie(
            id = 1,
            title = "Oppenheimer",
            posterPath = "/oppenheimer.jpg",
            releaseDate = "2023-07-21",
            description = "Um filme sobre a criação da bomba atômica"
        )

        val dto = movie.toDto()

        assertEquals(movie.id, dto.id)
        assertEquals(movie.title, dto.title)
        assertEquals(movie.getPosterUrl(), dto.imageUrl)
        assertEquals(movie.releaseDate, dto.releaseDate)
        assertEquals(movie.description, dto.description)
    }
}