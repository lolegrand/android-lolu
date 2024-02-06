package fr.enssat.bluetoothhid.lolu.ui.hidDetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.data.vo.Shortcut
import fr.enssat.bluetoothhid.lolu.R
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.component.tiles.ShortcutTile
import fr.enssat.bluetoothhid.lolu.ui.materialIcons

@Composable
fun HIDDetailContent(
    shortcuts: List<Shortcut>,
    onClickShortcut: (Shortcut) -> Unit,
    onCreateShortcut: () -> Unit,
    onDeleteShortcut: (Shortcut) -> Unit
) {
    // State
    var deleteModeActivated by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = GridCells.Fixed(3)
        ) {

            items(shortcuts) { shortcut ->
                ShortcutTile(
                    backgroundColor = Color(shortcut.backgroundRed, shortcut.backgroundGreen, shortcut.backgroundBlue),
                    iconColor = Color(shortcut.iconRed, shortcut.iconGreen, shortcut.iconBlue),
                    icon = materialIcons[shortcut.icon] ?: Icons.Default.Face,
                    onClick = {
                        if (deleteModeActivated) {
                            onDeleteShortcut(shortcut)
                        } else {
                            onClickShortcut(shortcut)
                        }
                    },
                    withDelete = deleteModeActivated
                )
            }
        }

        if (!deleteModeActivated) {
            LoLuButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
                    .height(50.dp),
                text = stringResource(id = R.string.hid_detail_create_shortcut),
                onClick = onCreateShortcut,
                type = ButtonType.PRIMARY
            )

            Spacer(modifier = Modifier.height(25.dp))
        }

        LoLuButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp)
                .height(50.dp),
            text = if (!deleteModeActivated) stringResource(id = R.string.hid_detail_delete_shortcut) else stringResource(id = R.string.hid_detail_end),
            onClick = {
                deleteModeActivated = !deleteModeActivated
            },
            type = ButtonType.DELETE
        )

    }
}