package fr.enssat.bluetoothhid.lolu.ui.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.R
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuDialog
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    HIDList: List<HID>,
    onHIDSelected: (HID) -> Unit,
    onCreateNewHid: () -> Unit,
    onDeleteHID: (HID) -> Unit
) {
    var deleteHid by remember {
        mutableStateOf<HID?>(null)
    }
    val hidList by remember(HIDList) {
        mutableStateOf(
            HIDList.sortedBy { it.name }.groupBy { it.name.first().toString() }.values.toList()
        )
    }

    Column (
        modifier = Modifier
            .background(color = LoLuAppTheme.colors.nuance100)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            hidList.forEach { hids ->
                stickyHeader {
                    Text(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 5.dp),
                        text = hids[0].name.first().toString().uppercase(Locale.ROOT),
                        style = LoLuAppTheme.typography.h1,
                        color = LoLuAppTheme.colors.primary
                    )
                    Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.LightGray)
                }

                items(hids, key = { hid -> hid.id}) { hid ->

                    val dismissState = rememberDismissState(
                        confirmValueChange = {
                            if (it == DismissValue.DismissedToStart) {
                                deleteHid = hid
                            }
                            false
                        }
                    )

                    SwipeToDismiss(
                        modifier = Modifier.animateItemPlacement(),
                        state = dismissState,
                        background = {
                            Row(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = LoLuAppTheme.colors.error)
                                    .padding(end = 10.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = LoLuAppTheme.colors.nuance100
                                )
                            }

                        },
                        dismissContent = {
                            HIDTile(
                                hid = hid,
                                onClick = { onHIDSelected(hid) }
                            )
                        },
                        directions = setOf(DismissDirection.EndToStart)
                    )
                }
            }
        }
        LoLuButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp, top = 20.dp)
                .height(50.dp),
            text = stringResource(id = R.string.home_create_new_hid),
            onClick = onCreateNewHid,
            type = ButtonType.PRIMARY
        )
    }

    if (deleteHid != null) {
        LoLuDialog(
            title = stringResource(id = R.string.home_delete_hid_title),
            actionText1 = stringResource(id = R.string.home_delete_hid_confirm),
            onClickAction1 = {
                onDeleteHID(deleteHid!!)
                deleteHid = null
            },
            actionText2 = stringResource(id = R.string.home_delete_hid_abord),
            onClickAction2 = {
                deleteHid = null
            },
            onDismiss = {
                deleteHid = null
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeContentPreview() {
    LoLuTheme {
        HomeContent(
            HIDList = listOf(
                HID("Coucou"),
                HID("Coucou"),
                HID("Coucou"),
                HID("Coucou"),
            ),
            onHIDSelected = {},
            onCreateNewHid = {},
            onDeleteHID = {})
    }
}
