package fr.enssat.bluetoothhid.lolu.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import fr.enssat.bluetoothhid.lolu.navigation.LoLuNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    
    LoLuNavHost(navController = navController)
}
