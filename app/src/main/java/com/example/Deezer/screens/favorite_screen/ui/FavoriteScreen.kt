package com.example.Deezer

import FavoriteViewModel
import FavoriteViewModelFactory
import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController

@Composable
fun FavoriteScreen(navController: NavHostController, application: Application) {
    val viewModel = ViewModelProvider(
        LocalContext.current as ComponentActivity,
        FavoriteViewModelFactory(application)
    ).get(FavoriteViewModel::class.java)

    val likedTracks = viewModel._likedTracks.value?.value // Extracting the value from LiveData

    TemplateScreen(
        navController = navController,
        title = "Favorites",
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (likedTracks.isNullOrEmpty()) {
                    Text(text = "No favorite tracks found")
                } else {
                    likedTracks.forEach { track ->
                        Text(text = track.title)
                        // Display more track details as needed
                    }
                }
            }
        },
        contentIsEmpty = likedTracks.isNullOrEmpty()
    )
}
/*

@Composable
fun FavoriteScreen(navController: NavHostController, application: Application) {
    val viewModel = ViewModelProvider(
        LocalContext.current as ComponentActivity,
        FavoriteViewModelFactory(application)
    ).get(FavoriteViewModel::class.java)

    //val likedTracksState = viewModel._likedTracks.value?.collectAsState(initial = emptyList()).value

    TemplateScreen(navController = navController, title = "Favorites",
        content = {
            /*LazyColumn {
                items(viewModel.likedTracks) { track ->
                    TrackCard("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhG_cZjxXIlwfsjseD7-LMSMzWI7txguoSLjCbwM85&s",track,
                        onclick = {
                            viewModel.playTrack(track)
                        },
                        onLikeClick = {
                            track.isLiked = !track.isLiked
                            viewModel.onLikeClick(track)
                        },
                        isPlaying = viewModel.currentPlayingTrack.value?.id == track.id,
                        viewModel = viewModel
                    )
                }
            }*/
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Will be completed later!")
            }
        },
        contentIsEmpty = false)

}

@Composable
fun FavoriteScreen(navController: NavHostController, application: Application) {
    val viewModel = ViewModelProvider(
        LocalContext.current as ComponentActivity,
        FavoriteViewModelFactory(application)
    ).get(FavoriteViewModel::class.java)

    val likedTracksState = viewModel._likedTracks.value?.collectAsState(initial = emptyList()).value

    TemplateScreen(
        navController = navController,
        title = "Favorites",
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (likedTracksState.value.isEmpty()) {
                    Text(text = "No favorite tracks found", color = Color.White)
                } else {
                    // Display liked tracks here
                    likedTracksState.value.forEach { track ->
                        Text(text = track.title) // Example: Displaying track title
                        // You can display more details of the track here
                    }
                }
            }
        },
        contentIsEmpty = likedTracksState.value.isEmpty()
    )
}*/