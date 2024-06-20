package com.example.Deezer.screens.category_screen.model

import com.google.gson.annotations.SerializedName
data class GenresResponse(
    @SerializedName("data")
    val data: List<Genre>
)
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("type")
    val type: String
)

