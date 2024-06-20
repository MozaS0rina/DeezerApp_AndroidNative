import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.Deezer.DeezerApplication
import com.example.Deezer.screens.album_screen.data.db.LikedTrackEntity
import com.example.Deezer.screens.album_screen.model.TrackData
import com.example.Deezer.screens.album_screen.utils.MusicController
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val _currentPlayingTrack = mutableStateOf<TrackData?>(null)
    val currentPlayingTrack: State<TrackData?> = _currentPlayingTrack//value holder in executia unei functii compose
    private val musicController = MusicController()
    private var lastTrackId: Long = -1L  // Change to Long
    private val trackDatabase = (application as DeezerApplication).trackDatabase
    val _likedTracks = MutableLiveData<LiveData<List<LikedTrackEntity>>>() // LiveData to hold the list of liked tracks


    init {
        fetchLikedTracks()
    }

    private fun fetchLikedTracks() {
        viewModelScope.launch {
            // Retrieve liked tracks from the database and update the LiveData
            _likedTracks.value = trackDatabase.getLikedTrackDao().getLikedTracks()
        }
    }

    fun onLikeClick(track: TrackData) = viewModelScope.launch {
        val trackEntity = LikedTrackEntity(
            trackId = track.id,
            title = track.title,
            duration = track.duration,
            preview = track.preview
        )
        if (track.isLiked) {
            trackDatabase.getLikedTrackDao().insertLikedTrack(trackEntity)
        } else {
            trackDatabase.getLikedTrackDao().deleteLikedTrack(trackEntity)
        }
    }

    fun playTrack(track: TrackData) {
        if (lastTrackId != track.id) {  // Comparison between Long and Long
            lastTrackId = track.id
        }

        musicController.playTrack(track, onSongComplete = {
            _currentPlayingTrack.value = null
            lastTrackId = -1L  // Use Long literal
        })
    }

    fun stopTrack() {
        musicController.stopTrack()
    }
}

class FavoriteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}