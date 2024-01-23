package fr.enssat.bluetoothhid.lolu.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class LoLuColors(
    val primary: Color,
    val primaryVariant: Color,
    val nuance100: Color,
    val error: Color,
    val background: Color
)

internal val LocalColors = staticCompositionLocalOf {
    LoLuColors(
        primary = Color.Unspecified,
        primaryVariant = Color.Unspecified,
        background = Color.Unspecified,
        nuance100 = Color.Unspecified,
        error = Color.Unspecified
    )
}