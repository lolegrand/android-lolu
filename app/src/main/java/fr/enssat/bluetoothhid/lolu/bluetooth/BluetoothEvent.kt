package fr.enssat.bluetoothhid.lolu.bluetooth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.Delegates

val bluetoothState by lazy { MutableStateFlow(BluetoothStatus.OFF) }

val bluetoothConnectionState by lazy { MutableStateFlow(BluetoothConnectionStatus.DISCONNECTED) }

var numberOfDeviceConnected by Delegates.observable(0) { prop, oldValue, newValue ->
    if (oldValue == newValue) return@observable

    if (newValue == 0) {
        bluetoothConnectionState.value = BluetoothConnectionStatus.DISCONNECTED
    } else {
        bluetoothConnectionState.value = BluetoothConnectionStatus.CONNECTED
    }
}
