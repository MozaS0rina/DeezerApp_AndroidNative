package com.example.Deezer.screens.album_screen.data.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LikedTrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikedTrack(likedTrackEntity: LikedTrackEntity)

   @Query("SELECT * FROM LikedTrackEntity")
    fun getLikedTracks(): LiveData<List<LikedTrackEntity>>

    @Delete
    suspend fun deleteLikedTrack(likedTrackEntity: LikedTrackEntity)
}