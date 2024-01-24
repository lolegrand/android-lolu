package fr.enssat.bluetoothhid.lolu.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.CreateShortcut
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.HIDDetail
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.Home
import fr.enssat.bluetoothhid.lolu.ui.createShortcut.CreateShortcutScreen
import fr.enssat.bluetoothhid.lolu.ui.hidDetail.HIDDetailScreen
import fr.enssat.bluetoothhid.lolu.ui.home.HomeScreen

@Composable
fun LoLuNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        // enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        // exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        startDestination = Home.route
    ) {
        composable(
            route = Home.route,
            arguments = Home.args()
        ) {
            HomeScreen(
                onNavigateToHid = { hid ->
                    navController.navigate(HIDDetail.createRoute(HIDDetail.HIDDetailArgs(hid.id)))
                }
            )
        }

        composable(
            route = HIDDetail.route,
            arguments = HIDDetail.args()
        ) {
            HIDDetailScreen(
                onClickCreateNewShortcut = { hid ->
                    navController.navigate(CreateShortcut.createRoute(CreateShortcut.CreateShortcutArgs(hid.id)))
                }
            )
        }

        composable(
            route = CreateShortcut.route,
            arguments = CreateShortcut.args()
        ) {
            CreateShortcutScreen(
                onCreationCompleted = {
                    navController.popBackStack(route = HIDDetail.route, inclusive = false)
                }
            )
        }
    }
}
