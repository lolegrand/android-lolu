package fr.enssat.bluetoothhid.lolu

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import fr.enssat.bluetoothhid.lolu.bluetooth.BluetoothManager

@HiltAndroidApp
class LoLuApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Init bluetooth
        initBluetooth()
    }

    private fun initBluetooth() {
        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter = bluetoothManager.bluetoothAdapter

    }

}