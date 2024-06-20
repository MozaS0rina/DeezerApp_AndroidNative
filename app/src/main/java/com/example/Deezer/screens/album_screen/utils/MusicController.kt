package com.example.Deezer.screens.album_screen.utils

import android.media.MediaPlayer
import com.example.Deezer.screens.album_screen.model.TrackData
import java.io.IOException

class MusicController {
    private var mediaPlayer: MediaPlayer? = null
    var currentTrack: TrackData? = null

    fun playTrack(track: TrackData, onSongComplete: () -> Unit) {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying && currentTrack == track) {
            stopTrack()
            return
        }

        stopTrack()

        mediaPlayer = MediaPlayer().apply {
            setOnPreparedListener { start() }
            setOnCompletionListener {
                releasePlayer()
                onSongComplete()
            }
            setOnErrorListener { _, what, extra ->
                // Handle errors here
                false
            }
            try {
                setDataSource(track.preview)
                prepareAsync()
            } catch (e: IOException) {
                // Handle IO exceptions
            }
            currentTrack = track
        }
    }

    fun stopTrack() {
        if (mediaPlayer != null) {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
            }
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
        currentTrack = null
    }

    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }
}