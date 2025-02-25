package com.br.films.data.model

import com.br.films.data.dto.MovieResponseDto
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val movies: List<Movie>
)

fun MovieResponse.toDto(): MovieResponseDto {
    return MovieResponseDto(
        movies = this.movies.map { it.toDto() }
    )
}