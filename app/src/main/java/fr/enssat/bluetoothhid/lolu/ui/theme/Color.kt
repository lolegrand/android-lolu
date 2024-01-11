package fr.enssat.bluetoothhid.lolu.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class LoLuColors(
    val primary: Color,
    val primaryVariant: Color,
    val background: Color,
    val nuance100: Color,
    val nuance98: Color,
    val nuance92: Color,
    val nuance90: Color,
    val nuance85: Color,
    val nuance70: Color,
    val nuance55: Color,
    val nuance35: Color,
    val nuance15: Color,
    val error: Color,
    val warning: Color,
    val success: Color,
    val cableYellow: Color,
    val cablePink: Color
)

internal val LocalColors = staticCompositionLocalOf {
    LoLuColors(
        primary = Color.Unspecified,
        primaryVariant = Color.Unspecified,
        background = Color.Unspecified,
        nuance100 = Color.Unspecified,
        nuance98 = Color.Unspecified,
        nuance92 = Color.Unspecified,
        nuance90 = Color.Unspecified,
        nuance85 = Color.Unspecified,
        nuance70 = Color.Unspecified,
        nuance55 = Color.Unspecified,
        nuance35 = Color.Unspecified,
        nuance15 = Color.Unspecified,
        error = Color.Unspecified,
        warning = Color.Unspecified,
        success = Color.Unspecified,
        cableYellow = Color.Unspecified,
        cablePink = Color.Unspecified
    )
}