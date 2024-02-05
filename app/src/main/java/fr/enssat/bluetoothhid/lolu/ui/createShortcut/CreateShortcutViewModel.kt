package fr.enssat.bluetoothhid.lolu.ui.createShortcut

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.enssat.bluetoothhid.data.repository.ShortcutRepository
import fr.enssat.bluetoothhid.data.vo.Shortcut
import fr.enssat.bluetoothhid.lolu.navigation.destination.impl.CreateShortcut
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateShortcutViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val shortcutRepository: ShortcutRepository
) : ViewModel() {

    // State
    private val hidID: Int = savedStateHandle.get<Int>(CreateShortcut.HID_ID) ?: 0

    private val _state = MutableStateFlow<CreateShortcutState>(CreateShortcutState.CreationStepOne)
    val state by lazy { _state.asStateFlow() }

    // Function
    fun createNewIconStepOne(bgRed: Float, bgGreen: Float, bgBlue: Float, icRed: Float, icGreen: Float, icBlue: Float, iconName: String) {
        viewModelScope.launch {
            _state.emit(CreateShortcutState.CreateStepTwo(
                PartialShortcut(
                    hidID = hidID,
                    icon = iconName,
                    backgroundRed = bgRed,
                    backgroundGreen = bgGreen,
                    backgroundBlue = bgBlue,
                    iconRed = icRed,
                    iconBlue = icBlue,
                    iconGreen = icGreen
                )
            ))
        }
    }

    fun createNewIconStepTwo(keyCode: String, modifier: Int, partialShortcut: PartialShortcut) {
        viewModelScope.launch {
            shortcutRepository.addShortcut(partialShortcut.toShortcut(keyCode, modifier))
        }
    }

    // Object state
    sealed interface CreateShortcutState {
        data object CreationStepOne : CreateShortcutState

        data class CreateStepTwo(
            val partialShortcut: PartialShortcut
        ) : CreateShortcutState
    }

    // Transition object
    data class PartialShortcut(
        val hidID: Int,
        val icon: String,
        val backgroundRed: Float,
        val backgroundGreen: Float,
        val backgroundBlue: Float,
        val iconRed: Float,
        val iconGreen: Float,
        val iconBlue: Float,
    ) {
        fun toShortcut(keyCode: String, modifier: Int): Shortcut = Shortcut(
            hidID = this.hidID,
            icon = this.icon,
            backgroundRed = this.backgroundRed,
            backgroundGreen = this.backgroundGreen,
            backgroundBlue = this.backgroundBlue,
            iconRed = this.iconRed,
            iconBlue = this.iconBlue,
            iconGreen = this.iconGreen,
            keyCode = keyCode,
            modifier = modifier
        )
    }
}