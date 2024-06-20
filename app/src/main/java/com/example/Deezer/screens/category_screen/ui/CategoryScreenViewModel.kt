package com.example.Deezer.screens.category_screen.ui

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Deezer.screens.category_screen.model.GenresResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch

class CategoryScreenViewModel : ViewModel() {
    //creates an observable state holder that holds a single value and can be observed for changes
    private val _myGenres = mutableStateOf<GenresResponse?>(null)
    val myGenres: State<GenresResponse?> get() = _myGenres


    fun decodeAndDeserializeJson(encodedJsonMyGenres: String) {
        viewModelScope.launch {
            //launch allows asynchronous operations to be performed without blocking the main thread
            //viewModelScope ensures that any coroutine launched within this scope will be automatically cancelled if the ViewModel is cleared
            // Decode the JSON string
            val jsonMyGenres = Uri.decode(encodedJsonMyGenres)
            // Deserialize the JSON string back to GenresResponse object
            val gson = Gson()
            _myGenres.value = gson.fromJson(jsonMyGenres, GenresResponse::class.java)
        }
    }
}
