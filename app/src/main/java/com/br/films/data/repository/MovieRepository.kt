package com.br.films.data.repository

import com.br.films.data.dto.MovieResponseDto

interface MovieRepository {
    suspend fun getPopularMovies(): Result<MovieResponseDto>
}