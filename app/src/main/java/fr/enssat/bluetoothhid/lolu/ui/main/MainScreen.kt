package fr.enssat.bluetoothhid.lolu.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import fr.enssat.bluetoothhid.lolu.R
import fr.enssat.bluetoothhid.lolu.bluetooth.BluetoothStatus
import fr.enssat.bluetoothhid.lolu.bluetooth.bluetoothState
import fr.enssat.bluetoothhid.lolu.bluetooth.getBluetoothPermissionsToAskForCurrentVersion
import fr.enssat.bluetoothhid.lolu.ext.enableBluetoothFeature
import fr.enssat.bluetoothhid.lolu.ext.navigateToAppSettings
import fr.enssat.bluetoothhid.lolu.navigation.LoLuNavHost
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuDialog

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {
    // Remember
    val navController = rememberNavController()
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
            title = stringResource(id = R.string.permission_dialog_title),
            subtitle = stringResource(id = R.string.permission_dialog_subtitle),
            actionText1 = stringResource(id = R.string.permission_dialog_validate),
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
    if (bluetoothPermissionState.allPermissionsGranted && bluetoothState == BluetoothStatus.OFF) {
        LoLuDialog(
            title = stringResource(id = R.string.bluetooth_dialog_title),
            subtitle = stringResource(id = R.string.bluetooth_dialog_subtitle),
            actionText1 = stringResource(id = R.string.bluetooth_dialog_validate),
            onClickAction1 = { context.enableBluetoothFeature() },
            onDismiss = { }
        )
    }

    // Content
    Scaffold { contentPadding ->
        LoLuNavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController
        )
    }
}
