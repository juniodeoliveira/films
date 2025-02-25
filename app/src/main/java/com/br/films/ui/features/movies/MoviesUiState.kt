package com.br.films.ui.features.movies

import com.br.films.data.dto.MovieResponseDto

data class MoviesUiState(
    val isLoading: Boolean = false,
    val movieResponseDto: MovieResponseDto = MovieResponseDto(emptyList()),
    val error: String? = null
)
