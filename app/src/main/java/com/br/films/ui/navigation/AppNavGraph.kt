package com.br.films.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.br.films.ui.features.moviedetail.MovieDetailsScreen
import com.br.films.ui.features.movies.MovieScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.List
    ) {
        composable<Routes.List> {
            MovieScreen(
                onMovieClick = { movie ->
                    navController.navigate(
                        Routes.Details(
                            title = movie.title,
                            imageUrl = movie.imageUrl,
                            releaseDate = movie.releaseDate,
                            description = movie.description
                        )
                    )
                }
            )
        }

        composable<Routes.Details> { backStackEntry ->
            val details: Routes.Details = backStackEntry.toRoute()

            MovieDetailsScreen(
                title = details.title,
                date = details.releaseDate,
                imageUrl = details.imageUrl,
                description = details.description,
                onBackPress = { navController.popBackStack() }
            )
        }
    }
}