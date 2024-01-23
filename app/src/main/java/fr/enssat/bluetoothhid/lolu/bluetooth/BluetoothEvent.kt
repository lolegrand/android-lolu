package fr.enssat.bluetoothhid.lolu.bluetooth

import kotlinx.coroutines.flow.MutableStateFlow

val bluetoothState by lazy { MutableStateFlow(BluetoothStatus.OFF) }