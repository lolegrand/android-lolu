package fr.enssat.bluetoothhid.lolu.bluetooth

import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class BluetoothManager(
    @ApplicationContext context: Context
) {

    val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
    val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()

}