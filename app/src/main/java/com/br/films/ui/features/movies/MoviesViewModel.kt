package com.br.films.ui.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.films.data.dto.MovieDto
import com.br.films.data.repository.MovieRepository
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<MovieDto>>()
    val movies: LiveData<List<MovieDto>> get() = _movies

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchPopularMovies() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getPopularMovies()
                .onSuccess { response ->
                    _movies.value = response.movies
                    _error.value = null
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Erro desconhecido"
                }
            _isLoading.value = false
        }
    }
}