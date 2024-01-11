package fr.enssat.bluetoothhid.lolu.bluetooth

import android.bluetooth.BluetoothAdapter
import fr.enssat.bluetoothhid.lolu.enum.BluetoothStatus
import kotlinx.coroutines.flow.MutableStateFlow

val bluetoothStateChangeEvent by lazy {
    MutableStateFlow(if (BluetoothAdapter.getDefaultAdapter().isEnabled) BluetoothStatus.ON else BluetoothStatus.OFF)
}


