package com.example.Deezer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ThemeViewModelFactory(private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")//ignora avertizarile cu numele din paranteza
            return ThemeViewModel(application) as T//returneaza instanta noua de tip generic atribuit la compilare
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
