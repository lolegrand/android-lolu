package fr.enssat.bluetoothhid.lolu.bluetooth

import android.Manifest
import android.os.Build

fun getBluetoothPermissionsToAskForCurrentVersion(): List<String> =
    if (isLocationPermissionNeededForBluetooth()) {
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH
        )
    } else listOf(
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_ADVERTISE
    )

fun isLocationPermissionNeededForBluetooth(): Boolean =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.S
