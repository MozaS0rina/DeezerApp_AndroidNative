package com.example.Deezer.screens.album_screen.data.db

class LikedTrackRepository(private val db: LikedTrackDatabase) {

    suspend fun insertLikedTrack(likedTrackEntity: LikedTrackEntity) =
        db.getLikedTrackDao().insertLikedTrack(likedTrackEntity)

    fun getLikedTracks() = db.getLikedTrackDao().getLikedTracks()

    suspend fun deleteLikedTrack(likedTrackEntity: LikedTrackEntity) =
        db.getLikedTrackDao().deleteLikedTrack(likedTrackEntity)
}