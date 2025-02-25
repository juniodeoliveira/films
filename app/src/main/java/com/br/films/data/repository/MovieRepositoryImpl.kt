package com.br.films.data.repository

import com.br.films.data.dto.MovieResponseDto
import com.br.films.data.model.toDto
import com.br.films.data.api.MovieApiService
import org.koin.core.annotation.Single

@Single
class MovieRepositoryImpl(private val apiService: MovieApiService) : MovieRepository {
    override suspend fun getPopularMovies(): Result<MovieResponseDto> {
        return try {
            val response = apiService.getPopularMovies()
            Result.success(response.toDto())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}