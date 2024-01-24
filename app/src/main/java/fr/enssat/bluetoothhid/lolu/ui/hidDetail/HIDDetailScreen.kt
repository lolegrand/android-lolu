package fr.enssat.bluetoothhid.lolu.ui.hidDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuLoader
import fr.enssat.bluetoothhid.lolu.ui.hidDetail.component.HIDDetailContent
import fr.enssat.bluetoothhid.lolu.ui.hidDetail.component.HIDDetailEmpty
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme

@Composable
fun HIDDetailScreen(
    viewModel: HIDDetailViewModel = hiltViewModel(),
    onClickCreateNewShortcut: (HID) -> Unit
) {

    val _state by viewModel.state.collectAsState()

    when(val state = _state) {
        is HIDDetailViewModel.HIDDetailState.Loading -> {
            LoLuLoader()
        }

        is HIDDetailViewModel.HIDDetailState.Loaded -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {


                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    text = state.hid.name,
                    style = LoLuAppTheme.typography.h1,
                    textAlign = TextAlign.Center
                )

                if (state.shortcuts.isEmpty()) {
                    HIDDetailEmpty {
                        onClickCreateNewShortcut(state.hid)
                    }
                } else {
                    HIDDetailContent(
                        shortcuts = state.shortcuts,
                        onClickShortcut = { shortcut ->
                            // TODO send command on bluetooth
                        },
                        onCreateShortcut = {
                            onClickCreateNewShortcut(state.hid)
                        },
                        onDeleteShortcut = {
                            viewModel.deleteShortcut(it)
                        }
                    )
                }
            }
        }
    }
}