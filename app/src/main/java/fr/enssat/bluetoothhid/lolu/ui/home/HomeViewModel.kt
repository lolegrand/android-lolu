package fr.enssat.bluetoothhid.lolu.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.enssat.bluetoothhid.lolu.bluetooth.LoLuBluetoothManager
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loLuBluetoothManager: LoLuBluetoothManager
) : ViewModel() {

}