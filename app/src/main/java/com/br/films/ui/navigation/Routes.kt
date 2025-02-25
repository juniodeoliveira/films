package com.br.films.ui.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object List: Routes()

    @Serializable
    data class Details(
        val title: String,
        val imageUrl: String,
        val releaseDate: String,
        val description: String
    ): Routes()
}