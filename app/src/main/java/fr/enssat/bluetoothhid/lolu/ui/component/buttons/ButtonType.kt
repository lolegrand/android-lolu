package fr.enssat.bluetoothhid.lolu.ui.component.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme

enum class ButtonType {
    DIALOG_ABORT,
    DIALOG_VALIDATE
}

val ButtonType.textColor: Color
    @Composable
    get() = when (this) {
        ButtonType.DIALOG_ABORT -> LoLuAppTheme.colors.nuance15
        ButtonType.DIALOG_VALIDATE -> LoLuAppTheme.colors.primary
    }


val ButtonType.backgroundColor: Color
    @Composable
    get() = when (this) {
        ButtonType.DIALOG_ABORT,
        ButtonType.DIALOG_VALIDATE -> Color.Transparent
    }
