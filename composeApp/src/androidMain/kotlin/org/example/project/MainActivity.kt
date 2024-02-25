package org.example.project

import App
import SettingsRepository
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.russhwolf.settings.SharedPreferencesSettings
import navigation.RootComponent

class MainActivity : ComponentActivity() {
    private val settingsRepository by lazy {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val settings = SharedPreferencesSettings(sharedPrefs)
        SettingsRepository(settings)
    }


    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = retainedComponent {
            RootComponent(it)
        }
        setContent {
            App(
                rootComponent = root,
                settingsRepository = settingsRepository
            )
        }
    }
}
