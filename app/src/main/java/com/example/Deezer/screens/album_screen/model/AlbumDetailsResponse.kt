package com.example.Deezer.screens.album_screen.model

import com.google.gson.annotations.SerializedName

data class AlbumDetailsResponse(
    @SerializedName("tracks")
    val tracks: Tracks,

    @SerializedName("release_date")
    val releaseDate: String,

    var albumName: String,

    var albumPicture: String
)

data class Tracks(
    @SerializedName("data")
    val data: List<TrackData>
)

class TrackData(
    @SerializedName("id")
    val id: Long,  // Changed from Int to Long

    @SerializedName("title")
    val title: String,

    @SerializedName("duration")
    val duration: Int,

    @SerializedName("preview")
    val preview: String,

    var isLiked: Boolean = false,
){

    fun getDurationInMinutes(): String {
        val minutes = duration / 60
        val seconds = duration % 60
        return String.format("%d:%02d", minutes, seconds)  // Ensure two-digit seconds format
    }

    fun getTitleDeletingAfterEncounterParentheses(): String {
        val index = title.indexOf("(")
        return if (index == -1) title else title.substring(0, index)
    }
}
