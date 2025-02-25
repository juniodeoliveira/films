package com.br.films.common

import com.br.films.data.model.Movie

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

fun Movie.getPosterUrl(): String {
    return "$BASE_IMAGE_URL${this.posterPath}"
}
