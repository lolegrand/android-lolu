package fr.enssat.bluetoothhid.lolu.ui.hidDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.enssat.bluetoothhid.data.repository.HIDRepository
import fr.enssat.bluetoothhid.data.repository.ShortcutRepository
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.data.vo.Shortcut
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.HIDDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HIDDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val hidRepository: HIDRepository,
    private val shortcutRepository: ShortcutRepository
): ViewModel() {

    // State management
    private val hidID: Int = savedStateHandle.get<Int>(HIDDetail.HID_ID) ?: 0

    private val _state = MutableStateFlow<HIDDetailState>(HIDDetailState.Loading)
    val state by lazy { _state.asStateFlow() }

    // Init
    init {
        viewModelScope.launch {
            hidRepository.watchHIDWithShortcutsById(hidID).collect { hidWithShortcuts ->
                _state.emit(HIDDetailState.Loaded(hidWithShortcuts.hid, hidWithShortcuts.shortcuts))
            }
        }
    }

    // Function
    fun deleteShortcut(shortcut: Shortcut) {
        viewModelScope.launch {
            shortcutRepository.deleteShortcut(shortcut)
        }
    }

    fun deleteHID(hid: HID) {
        viewModelScope.launch {
            hidRepository.deleteHID(hid)
        }
    }

    // State object
    sealed interface HIDDetailState {
        data object Loading : HIDDetailState

        data class Loaded(
            val hid: HID,
            val shortcuts: List<Shortcut>
        ) : HIDDetailState
    }
}