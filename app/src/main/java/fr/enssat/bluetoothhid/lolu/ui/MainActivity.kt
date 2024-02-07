package fr.enssat.bluetoothhid.lolu.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import fr.enssat.bluetoothhid.lolu.ui.main.MainScreen
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoLuTheme {
                MainScreen()
            }
        }
    }
}

