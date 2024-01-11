package fr.enssat.bluetoothhid.lolu.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.Home

@Composable
fun LoLuNavHost(
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Home.route
    ) {
        composable(route = Home.route) {

        }
    }
}
