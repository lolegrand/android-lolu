package fr.enssat.bluetoothhid.lolu.ui.component.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import fr.enssat.bluetoothhid.lolu.ui.theme.LoLuAppTheme

enum class ButtonType {
    DIALOG_ABORT,
    DIALOG_VALIDATE,
    PRIMARY,
    PRIMARY_REVERSED,
    DELETE
}

val ButtonType.textColor: Color
    @Composable
    get() = when (this) {
        ButtonType.DIALOG_ABORT -> Color.Black
        ButtonType.DIALOG_VALIDATE -> LoLuAppTheme.colors.primary
        ButtonType.PRIMARY -> LoLuAppTheme.colors.nuance100
        ButtonType.DELETE -> LoLuAppTheme.colors.nuance100
        ButtonType.PRIMARY_REVERSED -> LoLuAppTheme.colors.primary
    }


val ButtonType.backgroundColor: Color
    @Composable
    get() = when (this) {
        ButtonType.DIALOG_ABORT,
        ButtonType.DIALOG_VALIDATE -> Color.Transparent
        ButtonType.PRIMARY -> LoLuAppTheme.colors.primary
        ButtonType.DELETE -> LoLuAppTheme.colors.error
        ButtonType.PRIMARY_REVERSED -> LoLuAppTheme.colors.nuance100
    }

val ButtonType.shape: Shape
    get() = when(this) {
        ButtonType.DIALOG_ABORT,
        ButtonType.DIALOG_VALIDATE -> RectangleShape
        ButtonType.PRIMARY -> RoundedCornerShape(5.dp)
        ButtonType.DELETE -> RoundedCornerShape(5.dp)
        ButtonType.PRIMARY_REVERSED -> RoundedCornerShape(5.dp)
    }
