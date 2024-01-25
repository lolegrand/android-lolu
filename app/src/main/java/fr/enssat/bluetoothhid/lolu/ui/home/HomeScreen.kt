package fr.enssat.bluetoothhid.lolu.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuLoader
import fr.enssat.bluetoothhid.lolu.ui.home.component.HomeBottomSheetContent
import fr.enssat.bluetoothhid.lolu.ui.home.component.HomeContent
import fr.enssat.bluetoothhid.lolu.ui.home.component.HomeEmptyView
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import kotlinx.coroutines.launch


private val tabDate = listOf(
    "HID",
    "Bluetooth"
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToHid: (HID) -> Unit
) {
    // State
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()
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
            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    text = "LoLu's Project",
                    style = LoLuAppTheme.typography.h1,
                    textAlign = TextAlign.Center
                )

                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                            color = LoLuAppTheme.colors.primary,
                            height = 2.dp
                        )
                    }
                ) {
                    tabDate.forEachIndexed { index, s ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Text(
                                    modifier = Modifier,
                                    text = s,
                                    textAlign = TextAlign.Center,
                                    style = LoLuAppTheme.typography.h1,
                                    color = if (pagerState.currentPage == index) {
                                        LoLuAppTheme.colors.primary
                                    } else {
                                        Color.Black
                                    }
                                )
                            }
                        )
                    }
                }

                HorizontalPager(
                    modifier = Modifier,
                    state = pagerState
                ) { currentPage ->
                    when(currentPage) {
                        0 -> {
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
                        1 -> {
                            // TODO Display bluetooth
                        }
                    }
                }
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