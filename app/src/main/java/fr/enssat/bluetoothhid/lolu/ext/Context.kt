package fr.enssat.bluetoothhid.lolu.ext

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import fr.enssat.bluetoothhid.lolu.BuildConfig
import fr.enssat.bluetoothhid.lolu.bluetooth.getBluetoothPermissionsToAskForCurrentVersion

fun Context.isPermissionGranted(permission: String): Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.navigateToAppSettings() {
    startActivity(
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }, null
    )
}

@SuppressLint("MissingPermission")
fun Context.enableBluetoothFeature() {
    if (!getBluetoothPermissionsToAskForCurrentVersion().contains(Manifest.permission.BLUETOOTH_CONNECT) || isPermissionGranted(Manifest.permission.BLUETOOTH_CONNECT)) {
        BluetoothAdapter.getDefaultAdapter()?.let { bluetoothAdapter ->
            if (!bluetoothAdapter.isEnabled) {
                startActivity(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
            }
        }
    }
}
