package fr.enssat.bluetoothhid.lolu.ui.createShortcut

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.enssat.bluetoothhid.data.repository.ShortcutRepository
import fr.enssat.bluetoothhid.data.vo.Shortcut
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.CreateShortcut
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateShortcutViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val shortcutRepository: ShortcutRepository
) : ViewModel() {

    // State
    private val hidID: Int = savedStateHandle.get<Int>(CreateShortcut.HID_ID) ?: 0

    // Function
    fun createNewIcon(bgRed: Float, bgGreen: Float, bgBlue: Float, icRed: Float, icGreen: Float, icBlue: Float, iconName: String) {
        viewModelScope.launch {
            shortcutRepository.addShortcut(
                Shortcut(
                    hidID = hidID,
                    icon = iconName,
                    backgroundRed = bgRed,
                    backgroundGreen = bgGreen,
                    backgroundBlue = bgBlue,
                    iconRed = icRed,
                    iconBlue = icBlue,
                    iconGreen = icGreen
                )
            )
        }
    }

}