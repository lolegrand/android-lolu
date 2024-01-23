package fr.enssat.bluetoothhid.lolu.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.enssat.bluetoothhid.lolu.bluetooth.LoLuBluetoothManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel()