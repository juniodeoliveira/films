package com.br.films.ui.features.movies

sealed class MoviesAction {
    data object LoadMovies : MoviesAction()
}