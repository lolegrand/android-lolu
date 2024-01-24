package fr.enssat.bluetoothhid.lolu.ui.hidDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuLoader
import fr.enssat.bluetoothhid.lolu.ui.hidDetail.component.HIDDetailContent
import fr.enssat.bluetoothhid.lolu.ui.hidDetail.component.HIDDetailEmpty

@Composable
fun HIDDetailScreen(
    viewModel: HIDDetailViewModel = hiltViewModel(),
    onClickCreateNewShortcut: () -> Unit
) {

    val _state by viewModel.state.collectAsState()
    when(val state = _state) {
        is HIDDetailViewModel.HIDDetailState.Loading -> {
            LoLuLoader()
        }

        is HIDDetailViewModel.HIDDetailState.Loaded -> {
            if (state.shortcuts.isEmpty()) {
                HIDDetailEmpty {
                    onClickCreateNewShortcut()
                }
            } else {
                HIDDetailContent(
                    shortcuts = state.shortcuts,
                    onClickShortcut = { shortcut ->

                    }
                )
            }
        }
    }

}