package com.example.Deezer.screens.album_screen.model

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.graphics.Color
import com.example.Deezer.ui.theme.LightGreen

class BackgroundColorsOfTrackCards {
    val trackBackgroundColors = mutableStateMapOf<Long, Color>() // Changed to Long

    fun addColor(id: Long) {
        trackBackgroundColors[id] = Color.DarkGray
    }

    fun toggleColor(id: Long) { // Changed to Long
        trackBackgroundColors[id] = if (trackBackgroundColors[id] == Color.DarkGray) LightGreen else Color.DarkGray
    }

    fun getColor(id: Long): Color {
        return trackBackgroundColors[id] ?: Color.DarkGray
    }

    fun makeColorDarkGray(id: Long) { // Changed to Long
        if (id != -1L)
            trackBackgroundColors[id] = Color.DarkGray
    }
}