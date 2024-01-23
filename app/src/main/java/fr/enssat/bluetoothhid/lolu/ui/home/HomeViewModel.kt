package fr.enssat.bluetoothhid.lolu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.enssat.bluetoothhid.data.repository.HIDRepository
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.bluetooth.LoLuBluetoothManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hidRepository: HIDRepository
) : ViewModel() {

    // ViewModel init
    init {
        viewModelScope.launch {
            hidRepository.watchHDI().collect { hidList ->
                _state.emit(HomeState.Loaded(hidList))
            }
        }
    }

    // Function
    fun createNewHID(hid: HID) {
        viewModelScope.launch {
            hidRepository.insertHID(hid)
        }
    }

    // State management
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state by lazy { _state.asStateFlow() }

    // States object
    sealed interface HomeState {
        data object Loading: HomeState

        data class Loaded(
            val HIDList: List<HID>
        ): HomeState
    }
}