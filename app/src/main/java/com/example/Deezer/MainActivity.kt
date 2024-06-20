package com.example.Deezer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Deezer.ui.theme.DeezerTheme
import com.example.Deezer.navigation.MyAppNavHost

val LocalThemeViewModel = compositionLocalOf<ThemeViewModel> { error("ThemeViewModel not provided") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel(factory = ThemeViewModelFactory(application))

            CompositionLocalProvider(LocalThemeViewModel provides themeViewModel) {
                val isDarkMode = remember { themeViewModel.isDarkMode }

                isDarkMode.value?.let { darkMode ->
                    DeezerTheme(darkTheme = darkMode) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            MyAppNavHost(application = this@MainActivity.application as DeezerApplication)
                        }
                    }
                }
            }
        }
    }
}
