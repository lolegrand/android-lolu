package fr.enssat.bluetoothhid.lolu.ui.createShortcut.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.R
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.component.tiles.ShortcutTile
import fr.enssat.bluetoothhid.lolu.ui.materialIcons
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

@Composable
fun CreateShortcutContent(
    onCreateValidate: (Float, Float, Float, Float, Float, Float, String) -> Unit
) {
    // State
    var selectedIcon by remember { mutableStateOf("AccountBox") }
    var displayIconSelector by remember {
        mutableStateOf(false)
    }
    var outlineRed by remember { mutableFloatStateOf(0.0f) }
    var outlineGreen by remember { mutableFloatStateOf(0.0f) }
    var outlineBlue by remember { mutableFloatStateOf(0.0f) }
    var backgroundRed by remember { mutableFloatStateOf(1.0f) }
    var backgroundGreen by remember { mutableFloatStateOf(1.0f) }
    var backgroundBlue by remember { mutableFloatStateOf(1.0f) }

    val outlineColor by remember(outlineRed, outlineBlue, outlineGreen) {
        mutableStateOf(Color(outlineRed, outlineGreen, outlineBlue))
    }

    val backgroundColor by remember(backgroundRed, backgroundGreen, backgroundBlue) {
        mutableStateOf(Color(backgroundRed, backgroundGreen, backgroundBlue))
    }

    // View
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        ShortcutTile(
            modifier = Modifier.shadow(50.dp),
            backgroundColor = backgroundColor,
            iconColor = outlineColor,
            icon = materialIcons[selectedIcon] ?: Icons.Default.Face,
            onClick = { displayIconSelector = true }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.create_shortcut_icon_color),
            style = LoLuAppTheme.typography.h2
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            value = outlineRed,
            onValueChange = {
                outlineRed = it
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTickColor = Color.Red,
                activeTrackColor = Color.Red,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            value = outlineGreen,
            onValueChange = {
                outlineGreen = it
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTickColor = Color.Green,
                activeTrackColor = Color.Green,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            value = outlineBlue,
            onValueChange = {
                outlineBlue = it
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Blue,
                activeTickColor = Color.Blue,
                activeTrackColor = Color.Blue,
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.create_shortcut_bg_color),
            style = LoLuAppTheme.typography.h2,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            value = backgroundRed,
            onValueChange = {
                backgroundRed = it
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTickColor = Color.Red,
                activeTrackColor = Color.Red,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            value = backgroundGreen,
            onValueChange = {
                backgroundGreen = it
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTickColor = Color.Green,
                activeTrackColor = Color.Green,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            value = backgroundBlue,
            onValueChange = {
                backgroundBlue = it
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Blue,
                activeTickColor = Color.Blue,
                activeTrackColor = Color.Blue,
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        LoLuButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .height(50.dp),
            text = stringResource(id = R.string.create_shortcut_validate),
            onClick = {
                onCreateValidate(
                    backgroundColor.red,
                    backgroundColor.green,
                    backgroundColor.blue,
                    outlineColor.red,
                    outlineColor.green,
                    outlineColor.blue,
                    selectedIcon
                )
            },
            type = ButtonType.PRIMARY
        )
    }

    if (displayIconSelector) {
        IconSelectorDialog(
            onDismiss = {
                displayIconSelector = false
            },
            onIconSelected = {
                selectedIcon = it
                displayIconSelector = false
            }
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun CreateShortcutContentPreview() {
    LoLuTheme {
        CreateShortcutContent({ _, _, _, _, _, _, _ -> })
    }
}