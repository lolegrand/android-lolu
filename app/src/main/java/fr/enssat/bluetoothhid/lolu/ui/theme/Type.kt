package fr.enssat.bluetoothhid.lolu.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.LineHeightStyle
import fr.enssat.bluetoothhid.lolu.R

val Roboto = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal)
)

val RobotoCondensed = FontFamily(
    Font(R.font.roboto_condensed_bold, FontWeight.Bold),
    Font(R.font.roboto_condensed_light, FontWeight.Light),
    Font(R.font.roboto_condensed_regular, FontWeight.Normal)
)


@Immutable
data class LoLuTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val sm1: TextStyle,
    val sm2: TextStyle,
    val sm3: TextStyle,
    val p1: TextStyle,
    val p2: TextStyle,
    val log: TextStyle,
    val buttonL: TextStyle,
    val buttonS: TextStyle,
    val bigNumber: TextStyle
)

val Typography = LoLuTypography(
    h1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 27.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    h2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 1.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    sm1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    sm2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 19.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    sm3 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    p1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    p2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 19.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    log = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    buttonL = TextStyle(
        fontFamily = RobotoCondensed,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        letterSpacing = 1.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    buttonS = TextStyle(
        fontFamily = RobotoCondensed,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 18.sp,
        letterSpacing = 1.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    bigNumber = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 90.sp,
        lineHeight = 72.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.Both
        ),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )
)

val LocalTypography = staticCompositionLocalOf {
    LoLuTypography(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        sm1 = TextStyle.Default,
        sm2 = TextStyle.Default,
        sm3 = TextStyle.Default,
        p1 = TextStyle.Default,
        p2 = TextStyle.Default,
        log = TextStyle.Default,
        buttonL = TextStyle.Default,
        buttonS = TextStyle.Default,
        bigNumber = TextStyle.Default
    )
}
