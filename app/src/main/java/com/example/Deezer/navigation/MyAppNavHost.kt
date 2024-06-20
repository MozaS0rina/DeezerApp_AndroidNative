package com.example.Deezer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Deezer.DeezerApplication
import com.example.Deezer.FavoriteScreen
import com.example.Deezer.screens.album_screen.ui.AlbumDetailsScreen
import com.example.Deezer.screens.aritsts_screen.ui.ArtistsScreen
import com.example.Deezer.screens.artist_detail_screen.ui.ArtistDetailScreen
import com.example.Deezer.screens.category_screen.ui.CategoryScreen
import com.example.Deezer.screens.logo_screen.view.LogoScreen
import com.example.Deezer.screens.settings_screen.SettingsScreen

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "logo_screen",
    application: DeezerApplication
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("logo_screen") {
            // Navigate to CategoryScreen with myGenres as an argument
            LogoScreen(navController = navController)
        }
        composable("category_screen/{args}") { backStackEntry ->
            val myGenres = backStackEntry.arguments?.getString("args")
            if (myGenres != null) {
                CategoryScreen(navController,myGenres)
            }
        }
        composable("artists_screen/{genreId}/{categoryName}") { backStackEntry ->
            val genreId = backStackEntry.arguments?.getString("genreId")
            val categoryName = backStackEntry.arguments?.getString("categoryName")

            if (genreId != null && categoryName != null) {
                ArtistsScreen(navController, genreId, categoryName)
            }
        }
        composable("artist_detail_screen/{args}") { backStackEntry ->
            val artist = backStackEntry.arguments?.getString("args")
            if (artist != null) {
                ArtistDetailScreen(navController,artist)
            }
        }
        composable("album_details_screen/{args}") { backStackEntry ->
            val album = backStackEntry.arguments?.getString("args")
            if (album != null) {
                AlbumDetailsScreen(navController,album,application)
            }
        }

        composable("favorites_screen") {
            FavoriteScreen(navController = navController,application = application)
        }
        composable("settings_screen") {
            SettingsScreen(navController = navController,application = application)
        }
    }
}


