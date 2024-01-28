package fr.enssat.bluetoothhid.lolu.bluetooth

import android.bluetooth.BluetoothDevice
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val manager: LoLuBluetoothManager
): ViewModel() {

    private val _scanState = MutableStateFlow(false)
    val scanState by lazy { _scanState }

    val discoveredDevice by lazy { manager.discoveredDevices }

    val connectedDevice by lazy { manager.connectedDevice }

    fun startDiscovering() {
        manager.startDiscovering()
    }

    fun stopDiscovering() {
        manager.stopDiscovering()
    }

    fun connectToDevice(bluetoothDevice: BluetoothDevice) {
        manager.connectToDevice(bluetoothDevice)
    }

    init {
        viewModelScope.launch {
            while (true) {
                _scanState.emit(manager.isDiscovering())
                delay(1000)
            }
        }
    }
}