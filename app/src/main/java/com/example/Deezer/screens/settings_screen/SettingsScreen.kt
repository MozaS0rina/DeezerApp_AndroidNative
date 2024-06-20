package com.example.Deezer.screens.settings_screen

import android.app.Application
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.Deezer.TemplateScreen
import com.example.Deezer.ui.theme.DeezerTheme

@Composable
fun SettingsScreen(navController: NavHostController, application: Application) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    var isDarkMode by remember { mutableStateOf(sharedPreferences.getBoolean("isDarkMode", false)) }
    var name by remember { mutableStateOf(sharedPreferences.getString("userName", "") ?: "") }
    //mutableStateOf folosit pt obiecte observabile in compose
    DeezerTheme(darkTheme = isDarkMode) {
        TemplateScreen(navController = navController, title = "Settings",
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            isDarkMode = !isDarkMode
                            with(sharedPreferences.edit()) {
                                putBoolean("isDarkMode", isDarkMode)
                                apply()
                            }
                        },
                        modifier = Modifier.padding(vertical = 90.dp)
                    ) {
                        Text(text = if (isDarkMode) "Light Mode" else "Dark Mode")
                    }

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Enter your signature") },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            with(sharedPreferences.edit()) {
                                putString("userName", name)
                                apply()
                            }
                        }),
                        modifier = Modifier
                            .height(66.dp)
                            .fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            with(sharedPreferences.edit()) {
                                putString("userName", name)
                                apply()
                            }
                        },
                        modifier = Modifier.padding(vertical = 30.dp)
                    ) {
                        Text("Save Name")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AboutBox()
                }
            },
            contentIsEmpty = false
        )
    }
}

@Composable
fun AboutBox() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = "This Deezer Music App is a comprehensive music streaming application that provides users with a seamless music experience. With its user-friendly interface, personalized recommendations, and offline playback capabilities, the app is an excellent choice for music lovers looking for a reliable and enjoyable music streaming experience.",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
