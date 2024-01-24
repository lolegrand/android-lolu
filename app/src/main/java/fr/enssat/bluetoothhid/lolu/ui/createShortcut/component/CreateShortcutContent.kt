package fr.enssat.bluetoothhid.lolu.ui.createShortcut.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.ButtonType
import fr.enssat.bluetoothhid.lolu.ui.component.buttons.LoLuButton
import fr.enssat.bluetoothhid.lolu.ui.component.tiles.ShortcutTile
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuTheme

private fun computeColor(red: Float, green: Float, blue: Float): Color {
    return Color(red, green, blue)
}


@Composable
fun CreateShortcutContent(
    onCreateValidate: (Float, Float, Float, Float, Float, Float) -> Unit
) {
    // State
    var outlineRed by remember { mutableFloatStateOf(0.0f) }
    var outlineGreen by remember { mutableFloatStateOf(0.0f) }
    var outlineBlue by remember { mutableFloatStateOf(0.0f) }
    var backgroundRed by remember { mutableFloatStateOf(1.0f) }
    var backgroundGreen by remember { mutableFloatStateOf(1.0f) }
    var backgroundBlue by remember { mutableFloatStateOf(1.0f) }

    val outlineColor by remember(outlineRed, outlineBlue, outlineGreen) {
        mutableStateOf(computeColor(outlineRed, outlineGreen, outlineBlue))
    }

    val backgroundColor by remember(backgroundRed, backgroundGreen, backgroundBlue) {
        mutableStateOf(computeColor(backgroundRed, backgroundGreen, backgroundBlue))
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
            icon = Icons.Filled.FavoriteBorder,
            onClick = { }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Couleur de votre shortcut",
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
            text = "Couleur du background de votre shortcut",
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

        Spacer(modifier = Modifier.height(50.dp))


        LoLuButton(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            text = "Valider",
            onClick = {
                onCreateValidate(
                    backgroundColor.red,
                    backgroundColor.green,
                    backgroundColor.blue,
                    outlineColor.red,
                    outlineColor.green,
                    outlineColor.blue,
                )
            },
            type = ButtonType.PRIMARY
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun CreateShortcutContentPreview() {
    LoLuTheme {
        CreateShortcutContent({ _, _, _, _, _, _ -> })
    }
}