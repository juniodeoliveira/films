package com.br.films.ui.features.moviedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.br.films.R
import com.br.films.common.Dimens

@Composable
fun MovieDetailsScreen(
    title: String,
    imageUrl: String,
    date: String,
    description: String,
    onBackPress: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title, onBackPress)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
        ) {
            ContainerMovie(title, imageUrl, date, description)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(title: String, onBackPress: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.arrow_back_description),
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun ContainerMovie(
    title: String,
    imageUrl: String,
    date: String,
    description: String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.image_movie_description, title),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f / 3f)
    )

    Text(text = title, style = MaterialTheme.typography.headlineMedium)

    Text(
        text = stringResource(id = R.string.release_date, date),
        style = MaterialTheme.typography.bodyLarge
    )

    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium
    )
}