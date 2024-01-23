package fr.enssat.bluetoothhid.lolu.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuLoader
import fr.enssat.bluetoothhid.lolu.ui.home.component.HomeBottomSheetContent
import fr.enssat.bluetoothhid.lolu.ui.home.component.HomeContent
import fr.enssat.bluetoothhid.lolu.ui.home.component.HomeEmptyView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToHid: (HID) -> Unit
) {
    // State
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val _state by viewModel.state.collectAsState()
    var displayHIDCreationBottomSheet by remember {
        mutableStateOf(false)
    }


    // View
    when(val state = _state) {
        is HomeViewModel.HomeState.Loading -> {
            LoLuLoader()
        }

        is HomeViewModel.HomeState.Loaded -> {
            if (state.HIDList.isEmpty()) {
                HomeEmptyView {
                    displayHIDCreationBottomSheet = true
                }
            } else {
                HomeContent(
                    HIDList = state.HIDList,
                    onHIDSelected = { hid -> onNavigateToHid(hid) },
                    onCreateNewHid = { displayHIDCreationBottomSheet = true }
                )
            }
        }
    }

    // Bottom sheet
    if (displayHIDCreationBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                    displayHIDCreationBottomSheet = false
                }
            }
        ) {
            HomeBottomSheetContent(
                onValidateCreation = { hid ->
                    coroutineScope.launch {
                        bottomSheetState.hide()
                        displayHIDCreationBottomSheet = false
                    }
                    viewModel.createNewHID(hid)
                }
            )
        }
    }
}