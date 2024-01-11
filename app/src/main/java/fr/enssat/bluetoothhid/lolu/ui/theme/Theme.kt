package fr.enssat.bluetoothhid.lolu.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import fr.enssat.bluetoothhid.lolu.R

@Composable
fun LoLuTheme(content: @Composable () -> Unit) {
    val customColors = LoLuColors(
        primary = colorResource(id = R.color.lolu_primary),
        primaryVariant = colorResource(id = R.color.lolu_primary_variant),
        background = colorResource(id = R.color.lolu_nuance_98),
        nuance100 = colorResource(id = R.color.lolu_nuance_100),
        nuance98 = colorResource(id = R.color.lolu_nuance_98),
        nuance92 = colorResource(id = R.color.lolu_nuance_92),
        nuance90 = colorResource(id = R.color.lolu_nuance_90),
        nuance85 = colorResource(id = R.color.lolu_nuance_85),
        nuance70 = colorResource(id = R.color.lolu_nuance_70),
        nuance55 = colorResource(id = R.color.lolu_nuance_55),
        nuance35 = colorResource(id = R.color.lolu_nuance_35),
        nuance15 = colorResource(id = R.color.lolu_nuance_15),
        error = colorResource(id = R.color.lolu_error),
        warning = colorResource(id = R.color.lolu_warning),
        success = colorResource(id = R.color.lolu_success),
        cableYellow = colorResource(id = R.color.lolu_cable_yellow),
        cablePink = colorResource(id = R.color.lolu_cable_pink)
    )

    val selectionColors = remember(customColors.primary, customColors.background) {
        TextSelectionColors(
            handleColor = customColors.primary,
            backgroundColor = customColors.primary.copy(alpha = 0.4f)
        )
    }

    CompositionLocalProvider(
        LocalColors provides customColors,
        LocalIndication provides rememberRipple(),
        LocalRippleTheme provides MaterialRippleTheme,
        LocalTextSelectionColors provides selectionColors,
        LocalTypography provides Typography
    ) {
        ProvideTextStyle(value = Typography.p1) {
            content()
        }
    }
}

object LoLuAppTheme {
    val colors: LoLuColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: LoLuTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

}

@Immutable
private object MaterialRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color =
        RippleTheme.defaultRippleColor(
            contentColor = LocalContentColor.current,
            lightTheme = true // No dark theme
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            contentColor = LocalContentColor.current,
            lightTheme = true // No dark theme
        )
}