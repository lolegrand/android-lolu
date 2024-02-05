package fr.enssat.bluetoothhid.lolu.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.KeyEvent
import androidx.compose.runtime.collectAsState
import androidx.core.content.ContextCompat.RECEIVER_VISIBLE_TO_INSTANT_APPS
import androidx.core.content.ContextCompat.registerReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.enssat.bluetoothhid.data.vo.Shortcut
import fr.enssat.bluetoothhid.lolu.ui.KeyEventMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("MissingPermission")
@Singleton
class LoLuBluetoothManager @Inject constructor(
    @ApplicationContext context: Context
) {
    companion object {
        private val sdpRecord by lazy { BluetoothHidDeviceAppSdpSettings(
            "BTController LoLu",
            "Mobile BController",
            "ENSSAT",
            BluetoothHidDevice.SUBCLASS1_COMBO,
            DescriptorCollection.MOUSE_KEYBOARD_COMBO
        ) }
    }

    // Property
    private val bluetoothManager: BluetoothManager = context.getSystemService(BluetoothManager::class.java)
    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
    private var bluetoothHidDevice: BluetoothHidDevice? = null
    private val executor: Executor = Executors.newSingleThreadExecutor()
    private var mpluggedDevice: BluetoothDevice? = null

    // Event
    private val _connectedDevice = MutableStateFlow<BluetoothDevice?>(null)
    val connectedDevice by lazy { _connectedDevice.asStateFlow() }
    private val _discoveredDevices = MutableStateFlow(listOf<BluetoothDevice>())
    val discoveredDevices by lazy { _discoveredDevices.asStateFlow() }

    // Profile Listener
    private val profileListener = object : BluetoothProfile.ServiceListener {
        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
            if (profile == BluetoothProfile.HID_DEVICE) {
                bluetoothHidDevice = proxy as BluetoothHidDevice
                bluetoothHidDevice?.registerApp(
                    sdpRecord,
                    null,
                    null,
                    executor,
                    btAppCallback
                )
            }
        }

        override fun onServiceDisconnected(profile: Int) {
            if (profile == BluetoothProfile.HID_DEVICE) {
                bluetoothHidDevice = null
            }
        }
    }

    // Bt hid device connected
    val btAppCallback = object: BluetoothHidDevice.Callback() {

        override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
            super.onAppStatusChanged(pluggedDevice, registered)
            if (registered) {
                mpluggedDevice = pluggedDevice
                if (pluggedDevice != null && bluetoothHidDevice?.getConnectionState(pluggedDevice) == BluetoothProfile.STATE_DISCONNECTED) {
                    bluetoothHidDevice?.connect(pluggedDevice)
                }
            }
        }

        override fun onConnectionStateChanged(device: BluetoothDevice, state: Int) {
            super.onConnectionStateChanged(device, state)
            if (state == BluetoothProfile.STATE_CONNECTED) {
                stopDiscovering()
                _connectedDevice.value = device
                bluetoothHidDevice?.connect(device)
            } else {
                _connectedDevice.value = null
            }
        }

        override fun onSetReport(device: BluetoothDevice?, type: Byte, id: Byte, data: ByteArray?) {
            super.onSetReport(device, type, id, data)
        }

        override fun onGetReport(device: BluetoothDevice?, type: Byte, id: Byte, bufferSize: Int) {
            super.onGetReport(device, type, id, bufferSize)
        }
    }

    // Broadcast receiver
    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            when(action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    if (device?.name != null && !_discoveredDevices.value.contains(device) ) {
                        val currentDiscoveredDevice = _discoveredDevices.value.toMutableList()
                        currentDiscoveredDevice.add(device)
                        _discoveredDevices.value = currentDiscoveredDevice
                    }
                }
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

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(context, receiver, filter, RECEIVER_VISIBLE_TO_INSTANT_APPS)
    }

    // Function
    fun startDiscovering() {
        _discoveredDevices.value = listOf()
        bluetoothAdapter?.startDiscovery()
    }

    fun stopDiscovering() {
        bluetoothAdapter?.cancelDiscovery()
    }

    fun isDiscovering(): Boolean {
        return bluetoothAdapter?.isDiscovering ?: false
    }

    fun connectToDevice(bluetoothDevice: BluetoothDevice) {
        bluetoothHidDevice?.connect(bluetoothDevice)
    }

    fun sendShortcut(shortcut: Shortcut) {
        val connectedDevice = connectedDevice.value
        val btHidDevice = bluetoothHidDevice

        if (connectedDevice != null && btHidDevice != null) {
            val payload = byteArrayOf(
                shortcut.modifier.toByte(),
                0,
                KeyEventMap[KeyEvent.keyCodeFromString(shortcut.keyCode)]!!.toByte()
            )

            btHidDevice.sendReport(connectedDevice, 8, payload) // Send event
            btHidDevice.sendReport(connectedDevice, 8, byteArrayOf(0, 0, 0)) // release event
        }
    }
}