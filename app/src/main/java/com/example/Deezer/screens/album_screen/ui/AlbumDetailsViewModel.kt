package com.example.Deezer.screens.album_screen.ui


import android.app.Application
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.Deezer.DeezerApplication
import com.example.Deezer.screens.album_screen.data.db.LikedTrackEntity
import com.example.Deezer.screens.album_screen.data.db.LikedTrackRepository
import com.example.Deezer.screens.album_screen.model.AlbumDetailsResponse
import com.example.Deezer.screens.album_screen.model.BackgroundColorsOfTrackCards
import com.example.Deezer.screens.album_screen.model.TrackData
import com.example.Deezer.screens.album_screen.utils.MusicController
import com.google.gson.Gson
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentPlayingTrack = mutableStateOf<TrackData?>(null)
    val currentPlayingTrack: State<TrackData?> = _currentPlayingTrack//holder de valoare cand se executa o f. composable
    val backgroundColors = BackgroundColorsOfTrackCards()
    private val musicController = MusicController()
    private var lastTrackId: Long = -1
    private val likedTrackRepository: LikedTrackRepository by lazy { (application as DeezerApplication).likedTrackRepository }


    private val _likedTracks = mutableStateOf<List<LikedTrackEntity>?>(null)
    val likedTracks: State<List<LikedTrackEntity>?> = _likedTracks
    init {
        loadLikedTracks()
    }

    fun onLikeClick(track: TrackData) = viewModelScope.launch {
        val trackEntity = LikedTrackEntity(
            trackId = track.id,
            title = track.title,
            duration = track.duration,
            preview = track.preview
        )
        if (track.isLiked) {
            likedTrackRepository.insertLikedTrack(trackEntity)
        } else {
            likedTrackRepository.deleteLikedTrack(trackEntity)
        }
        // Reload liked tracks after the like status changes
        loadLikedTracks()
    }

    fun playTrack(track: TrackData) {
        if (lastTrackId != track.id) {
            backgroundColors.makeColorDarkGray(lastTrackId)
            lastTrackId = track.id
        }

        musicController.playTrack(track, onSongComplete = {
            _currentPlayingTrack.value = null
            backgroundColors.makeColorDarkGray(track.id)
            lastTrackId = -1
        })

        backgroundColors.toggleColor(track.id)
    }

    fun stopTrack() {
        musicController.stopTrack()
    }

    fun decodeAndDeserializeJson(encodedJsonAlbum: String): AlbumDetailsResponse {
        val jsonAlbum = Uri.decode(encodedJsonAlbum)
        val gson = Gson()
        return gson.fromJson(jsonAlbum, AlbumDetailsResponse::class.java)
    }

    private fun loadLikedTracks() {
        viewModelScope.launch {
            likedTrackRepository.getLikedTracks().observeForever { tracks ->
                _likedTracks.value = tracks
            }
        }
    }

}

class AlbumDetailsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumDetailsViewModel::class.java)) {
            return AlbumDetailsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}