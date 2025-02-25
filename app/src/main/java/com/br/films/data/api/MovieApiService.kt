package com.br.films.data.api

import com.br.films.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3d4e89fbf5bbc61ed8d0b85ccd888a3a"

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): MovieResponse
}