package fr.enssat.bluetoothhid.lolu.ui.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import fr.enssat.bluetoothhid.lolu.bluetooth.BluetoothStatus
import fr.enssat.bluetoothhid.lolu.bluetooth.bluetoothState
import fr.enssat.bluetoothhid.lolu.bluetooth.getBluetoothPermissionsToAskForCurrentVersion
import fr.enssat.bluetoothhid.lolu.ext.enableBluetoothFeature
import fr.enssat.bluetoothhid.lolu.ext.navigateToAppSettings
import fr.enssat.bluetoothhid.lolu.navigation.LoLuNavHost
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.Home
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuDialog
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    // Remember
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bluetoothState by bluetoothState.collectAsStateWithLifecycle()
    val bluetoothPermissionState: MultiplePermissionsState = rememberMultiplePermissionsState(
        permissions = getBluetoothPermissionsToAskForCurrentVersion()
    )
    var hasAskedPermissionOnceBluetooth by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    // Dialog permission
    if (!bluetoothPermissionState.allPermissionsGranted) {
        LoLuDialog(
            title = "Permission",
            subtitle = "L'application à besoin de votre permission pour utiliser le bluetooth.",
            actionText1 = "Compris",
            onClickAction1 = {
                if (!hasAskedPermissionOnceBluetooth) {
                    bluetoothPermissionState.launchMultiplePermissionRequest()
                    hasAskedPermissionOnceBluetooth = true
                } else {
                    context.navigateToAppSettings()
                }
            },
            onDismiss = {
                hasAskedPermissionOnceBluetooth = true
            }
        )
    }

    // Dialog bluetooth off
    /*
        LoLuDialog(
            title = "Bluetooth",
            subtitle = "Il s'emblerais que votre bluetooth soit désactiver, merci de l'activé.",
            actionText1 = "Compris",
            onClickAction1 = { context.enableBluetoothFeature() },
            onDismiss = { }
        )
    }*/

    // Content
    Scaffold { contentPadding ->
        LoLuNavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController
        )
    }
}
