package com.example.Deezer.screens.logo_screen.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.Deezer.screens.category_screen.data.DeezerCategoryApiHelper
import com.example.Deezer.ui.theme.YTMusic
import com.google.gson.Gson

@Composable
fun LogoScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    //Composable function in Jetpack Compose which will be executed when the Composable is first launched
    LaunchedEffect(Unit) {
        val myGenres = DeezerCategoryApiHelper.fetchGenres()

        //creates an instance of the Gson library
        val gson = Gson()
        //converts the myGenres object into a JSON string and stores it in the variable jsonMyGenres
        val jsonMyGenres = gson.toJson(myGenres)

        // Encode the JSON string
        val encodedJsonMyGenres = Uri.encode(jsonMyGenres)

        // Navigate to CategoryScreen with myGenres as an argument
        navController.navigate("category_screen/$encodedJsonMyGenres") {
            popUpTo("logo_screen") {
                inclusive = true
            }
        }
    }

    val backgroundColor = if (MaterialTheme.colors.isLight) LightGray else YTMusic

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = com.example.Deezer.R.drawable.deezer_logo_dark),
            contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth(0.80f) // This makes the Image take up half of the screen width
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
        )
    }
}
