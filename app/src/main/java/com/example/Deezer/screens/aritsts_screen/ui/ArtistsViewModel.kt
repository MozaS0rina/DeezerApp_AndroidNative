package com.example.Deezer.screens.aritsts_screen.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Deezer.screens.aritsts_screen.data.DeezerArtistApiHelper
import com.example.Deezer.screens.aritsts_screen.model.ArtistsResponse
import kotlinx.coroutines.launch

class ArtistsViewModel : ViewModel() {
    var artists by mutableStateOf<ArtistsResponse?>(null)
    // creates an observable state holder that holds a single value and can be observed for changes
    var isLoading by mutableStateOf(true)

    fun fetchArtists(genreId: String) {
        //launch allows asynchronous operations to be performed without blocking the main thread
        //viewModelScope ensures that any coroutine launched within this scope will be automatically cancelled if the ViewModel is cleared
        viewModelScope.launch {
            try {
                isLoading = true
                artists = DeezerArtistApiHelper.fetchArtists(genreId)
            } catch (e: Exception) {
                // handle error
            } finally {
                isLoading = false
            }
        }
    }
}
