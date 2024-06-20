package com.example.Deezer.screens.album_screen.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikedTrackEntity(
    @PrimaryKey val trackId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "duration") val duration: Int,
    @ColumnInfo(name = "preview") val preview: String
)