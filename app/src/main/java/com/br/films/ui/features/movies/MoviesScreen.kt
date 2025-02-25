package com.br.films.ui.features.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.br.films.R
import com.br.films.common.Dimens
import com.br.films.data.dto.MovieDto
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieScreen(onMovieClick: (MovieDto) -> Unit, viewModel: MoviesViewModel = koinViewModel()) {
    val movies by viewModel.movies.observeAsState(emptyList())
    val errorMessage by viewModel.error.observeAsState(null)
    val isLoading by viewModel.isLoading.observeAsState(false)

    LaunchedEffect(viewModel) {
        viewModel.fetchPopularMovies()
    }

    Scaffold(
        topBar = {
            TopBar()
        }
    ) { padding ->
        when {
            isLoading -> {
                LoadingScreen()
            }

            errorMessage != null -> {
                ErrorScreen(onRetry = { viewModel.fetchPopularMovies() })
            }

            movies.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(movies) { movie ->
                        MovieItem(Modifier.clickable { onMovieClick.invoke(movie) }, movie)
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun MovieItem(modifier: Modifier, movie: MovieDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.paddingMedium)
            .clip(RoundedCornerShape(Dimens.cornerRadiusMedium))
            .then(modifier),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(Dimens.cornerElevationSmall)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.paddingMedium)
        ) {
            Box(
                modifier = Modifier
                    .width(Dimens.imageSizeLarge)
                    .aspectRatio(2f / 3f)
            ) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = stringResource(
                        R.string.image_movie_description,
                        movie.title
                    ),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(Dimens.paddingMedium))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(Dimens.paddingSmall))

                Text(
                    text = stringResource(
                        id = R.string.release_date,
                        movie.releaseDate
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(Dimens.paddingLarge)
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.error_generic),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Dimens.paddingMedium))

            Button(onClick = onRetry) {
                Text(stringResource(R.string.try_again))
            }
        }
    }
}