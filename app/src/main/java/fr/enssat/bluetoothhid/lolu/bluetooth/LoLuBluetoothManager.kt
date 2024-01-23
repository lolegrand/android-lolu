package fr.enssat.bluetoothhid.lolu.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("MissingPermission")
@Singleton
class LoLuBluetoothManager @Inject constructor(
    @ApplicationContext context: Context
) {
    // Property
    private val bluetoothManager: BluetoothManager = context.getSystemService(BluetoothManager::class.java)

    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter

    private var bluetoothHidDevice: BluetoothHidDevice? = null

    private val _connectedDevices = MutableStateFlow(listOf<BluetoothDevice>())
    val connectedDevices by lazy { _connectedDevices.asStateFlow() }

    fun queryConnectedDevice() {
        _connectedDevices.value = bluetoothAdapter?.bondedDevices?.toList() ?: listOf()
        Log.wtf("Louis", "${bluetoothHidDevice?.connectedDevices}")
    }

    // Profile Listener
    private val profileListener = object : BluetoothProfile.ServiceListener {
        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
            if (profile == BluetoothProfile.HID_DEVICE) {
                bluetoothHidDevice = proxy as BluetoothHidDevice
                queryConnectedDevice()
            }
        }

        override fun onServiceDisconnected(profile: Int) {
            if (profile == BluetoothProfile.HID_DEVICE) {
                bluetoothHidDevice = null
            }
        }
    }

    // Init
    init {
        if (bluetoothAdapter == null) {
            bluetoothState.value = BluetoothStatus.OFF
        } else if (bluetoothAdapter.isEnabled) {
            bluetoothState.value = BluetoothStatus.ON
        } else {
            bluetoothState.value = BluetoothStatus.OFF
        }

        bluetoothAdapter?.getProfileProxy(context, profileListener, BluetoothProfile.HID_DEVICE)
    }
}