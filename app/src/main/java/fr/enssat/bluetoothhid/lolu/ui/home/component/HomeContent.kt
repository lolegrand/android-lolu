package fr.enssat.bluetoothhid.lolu.ui.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    HIDList: List<HID>,
    onHIDSelected: (HID) -> Unit,
    onCreateNewHid: () -> Unit
) {
    val hidList by remember(HIDList) {
        mutableStateOf(
            HIDList.sortedBy { it.name }.groupBy { it.name.first().toString() }.values.toList()
        )
    }

    Column (
        modifier = Modifier
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
                        modifier = Modifier.padding(start = 10.dp),
                        text = hids[0].name.first().toString(),
                        style = LoLuAppTheme.typography.h1,
                        color = LoLuAppTheme.colors.primary
                    )
                }

                itemsIndexed(hids) { idx, hid ->
                    HIDTile(
                        hid = hid,
                        onClick = { onHIDSelected(hid) }
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
            text = "Créer un nouvel HID",
            onClick = onCreateNewHid,
            type = ButtonType.PRIMARY
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
            onCreateNewHid = {})
    }
}
