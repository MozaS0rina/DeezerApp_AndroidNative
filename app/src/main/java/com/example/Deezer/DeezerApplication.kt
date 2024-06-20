package com.example.Deezer

import android.app.Application
import androidx.room.Room
import com.example.Deezer.screens.album_screen.data.db.LikedTrackDatabase
import com.example.Deezer.screens.album_screen.data.db.LikedTrackRepository

class DeezerApplication : Application() {

    // Instance of the Room database
    lateinit var trackDatabase: LikedTrackDatabase

    // Instance of the LikedTrackRepository
    lateinit var likedTrackRepository: LikedTrackRepository

    override fun onCreate() {
        super.onCreate()

        // Initialize the Room database
        trackDatabase = Room.databaseBuilder(
            applicationContext,
            LikedTrackDatabase::class.java, "deezer-database"
        ).build()

        // Initialize the LikedTrackRepository
        likedTrackRepository = LikedTrackRepository(trackDatabase)
    }
}