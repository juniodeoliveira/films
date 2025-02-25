package com.br.films.data.model

import com.br.films.common.getPosterUrl
import com.br.films.data.dto.MovieDto
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("overview") val description: String?
)

fun Movie.toDto(): MovieDto {
    return MovieDto(
        id = this.id,
        title = this.title,
        imageUrl = this.getPosterUrl(),
        releaseDate = this.releaseDate ?: "N/A",
        description = description ?: ""
    )
}
