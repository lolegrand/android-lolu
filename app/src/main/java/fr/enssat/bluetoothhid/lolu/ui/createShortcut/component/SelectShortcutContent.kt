package fr.enssat.bluetoothhid.lolu.ui.createShortcut.component

import android.view.KeyEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.ui.KeyEventMap
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuDropdownSelector
import fr.enssat.bluetoothhid.lolu.ui.component.LoLuRadioButton
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.component.tiles.ShortcutTile
import fr.enssat.bluetoothhid.lolu.ui.createShortcut.CreateShortcutViewModel
import fr.enssat.bluetoothhid.lolu.ui.materialIcons
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun SelectShortcutContent(
    partialShortcut: CreateShortcutViewModel.PartialShortcut,
    onFinishCreation: (String, Int) -> Unit
) {
    var selectedKeyEvent by remember {
        mutableStateOf(KeyEvent.keyCodeToString(KeyEvent.KEYCODE_A)) // Dirty move, no time to fix.
    }

    val isModifierSelected = mapOf(
        "LEFT_CONTROL" to remember { mutableStateOf(false) },
        "LEFT_ALT" to remember { mutableStateOf(false) },
        "LEFT_GUI" to remember { mutableStateOf(false) },
        "RIGHT_ALT" to remember { mutableStateOf(false) },
        "RIGHT_GUI" to remember { mutableStateOf(false) }
    )

    val onCompleteShortcut: () -> Unit = {
        var modifier = 0b0
        if (isModifierSelected["LEFT_CONTROL"]?.value!!) {
            modifier = modifier or 0b1
        }

        if (isModifierSelected["LEFT_ALT"]?.value!!) {
            modifier = modifier or 0b100
        }

        if (isModifierSelected["LEFT_GUI"]?.value!!) {
            modifier = modifier or 0b1000
        }

        if (isModifierSelected["RIGHT_ALT"]?.value!!) {
            modifier = modifier or 0b100_0000
        }

        if (isModifierSelected["RIGHT_GUI"]?.value!!) {
            modifier = modifier or 0b1000_0000
        }

        modifier = modifier and 0b1111_1111 // Sanitizing

        onFinishCreation(selectedKeyEvent, modifier)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        ShortcutTile(
            backgroundColor = Color(partialShortcut.backgroundRed, partialShortcut.backgroundGreen, partialShortcut.backgroundBlue),
            iconColor = Color(partialShortcut.iconRed, partialShortcut.iconGreen, partialShortcut.iconBlue),
            icon = materialIcons[partialShortcut.icon] ?: Icons.Default.Face,
            onClick = null
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            color = LoLuAppTheme.colors.primary,
            text = "Selectionnez un modifier",
            style = LoLuAppTheme.typography.h1,
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 15.dp),
            columns = GridCells.Fixed(2)
        ) {
            item {
                var isSelected by isModifierSelected["LEFT_CONTROL"]!!

                LoLuRadioButton(
                    isSelected = isSelected,
                    text = "LEFT_CONTROL",
                    onClick = { isSelected = !isSelected }
                )
            }

            item {
                var isSelected by isModifierSelected["LEFT_ALT"]!!

                LoLuRadioButton(
                    isSelected = isSelected,
                    text = "LEFT_ALT",
                    onClick = { isSelected = !isSelected }
                )
            }

            item {
                var isSelected by isModifierSelected["LEFT_GUI"]!!

                LoLuRadioButton(
                    isSelected = isSelected,
                    text = "LEFT_GUI",
                    onClick = { isSelected = !isSelected }
                )
            }

            item {
                var isSelected by isModifierSelected["RIGHT_ALT"]!!

                LoLuRadioButton(
                    isSelected = isSelected,
                    text = "RIGHT_ALT",
                    onClick = { isSelected = !isSelected }
                )
            }

            item {
                var isSelected by isModifierSelected["RIGHT_GUI"]!!

                LoLuRadioButton(
                    isSelected = isSelected,
                    text = "RIGHT_GUI",
                    onClick = { isSelected = !isSelected }
                )
            }

        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            color = LoLuAppTheme.colors.primary,
            text = "Selectionnez une touche",
            style = LoLuAppTheme.typography.h1,
        )

        Spacer(modifier = Modifier.height(10.dp))

        LoLuDropdownSelector(
            items = KeyEventMap.keys.map { KeyEvent.keyCodeToString(it) },
            selectedItem = selectedKeyEvent,
            onItemSelected = { selectedKeyEvent = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        LoLuButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .padding(horizontal = 25.dp)
                .height(50.dp),
            text = "Valider",
            onClick = onCompleteShortcut,
            type = ButtonType.PRIMARY
        )

    }
}