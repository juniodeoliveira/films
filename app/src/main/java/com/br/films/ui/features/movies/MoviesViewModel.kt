package com.br.films.ui.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.films.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> = _uiState

    fun sendAction(action: MoviesAction) {
        when (action) {
            is MoviesAction.LoadMovies -> fetchMovies()
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(isLoading = true)
            try {
                val movies = repository.getPopularMovies().getOrThrow()
                _uiState.value = uiState.value.copy(isLoading = false, movieResponseDto = movies)
            } catch (e: Exception) {
                _uiState.value =
                    uiState.value.copy(isLoading = false, error = "Erro ao carregar filmes")
            }
        }
    }
}