package fr.enssat.bluetoothhid.lolu.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BluetoothReceiver : BroadcastReceiver() {

    @Inject
    lateinit var loLuBluetoothManager: LoLuBluetoothManager

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {
                BluetoothAdapter.STATE_ON -> {
                    if (bluetoothState.value != BluetoothStatus.ON) {
                        bluetoothState.value = BluetoothStatus.ON
                    }
                }

                BluetoothAdapter.STATE_OFF -> {
                    if (bluetoothState.value != BluetoothStatus.OFF) {
                        bluetoothState.value = BluetoothStatus.OFF
                    }
                }
            }
        }

        if (intent.action == BluetoothDevice.ACTION_ACL_CONNECTED || intent.action == BluetoothDevice.ACTION_ACL_DISCONNECTED) {
            loLuBluetoothManager.queryConnectedDevice()
        }
    }
}
