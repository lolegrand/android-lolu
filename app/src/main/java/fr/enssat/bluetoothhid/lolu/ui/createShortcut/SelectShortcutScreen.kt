package fr.enssat.bluetoothhid.lolu.ui.createShortcut

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.enssat.bluetoothhid.lolu.ui.createShortcut.component.SelectShortcutContent

@Composable
fun SelectShortcutScreen(
    viewModel: CreateShortcutViewModel = hiltViewModel(),
    onShortcutCreationFinished: () -> Unit
) {

    val _state by viewModel.state.collectAsState()
    when (val state = _state) {
        is CreateShortcutViewModel.CreateShortcutState.CreateStepTwo -> {
            SelectShortcutContent(
                partialShortcut = state.partialShortcut,
                onFinishCreation = { keyCode, modifier ->
                    viewModel.createNewIconStepTwo(keyCode, modifier, state.partialShortcut)
                    onShortcutCreationFinished()
                }
            )
        }

        else -> {}
    }

}