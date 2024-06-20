package com.example.Deezer

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    private val _isDarkMode = MutableLiveData(sharedPreferences.getBoolean("isDarkMode", false))
    val isDarkMode: LiveData<Boolean> = _isDarkMode

    fun toggleDarkMode() {
        val newMode = !_isDarkMode.value!!
        _isDarkMode.value = newMode
        with(sharedPreferences.edit()) {
            putBoolean("isDarkMode", newMode)
            apply()
        }
    }

    fun setDarkMode(isDark: Boolean) {
        _isDarkMode.value = isDark
        with(sharedPreferences.edit()) {
            putBoolean("isDarkMode", isDark)
            apply()
        }
    }
}
