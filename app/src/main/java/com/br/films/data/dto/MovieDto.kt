package com.br.films.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
    val description: String
)